package com.cryptograph.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cryptograph.config.AppConfig;
import com.cryptograph.config.CacheKey;
import com.cryptograph.config.Market;
import com.cryptograph.generated.models.Binance;
import com.cryptograph.generated.models.Bittrex;
import com.cryptograph.generated.models.QuoteModel;
import com.cryptograph.generated.models.Result;
import com.cryptograph.utils.JacksonParser;
import com.cryptograph.utils.QuotesPopulator;
import com.cryptograph.utils.SymbolMaker;
import com.cryptogrpah.cm.CacheManager;

@Service
public class QuoteService implements IQuoteService
{
    private static final Logger LOG = LogManager.getLogger(QuoteService.class);

    @Override
    @Async
    public CompletableFuture<Optional<QuoteModel>> getQuoteFromBinance(String symbol)
    {
        if (StringUtils.isBlank(symbol)) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }

        boolean inCache = CacheManager.isInCache(CacheKey.BINANCE);
        if (inCache) {
            return CompletableFuture.completedFuture(Optional.ofNullable(CacheManager.getFromCache(CacheKey.BINANCE)));
        }
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AppConfig.getBinanceApi());
        urlBuilder.append(symbol.toUpperCase());
        QuoteModel quoteModel = new QuoteModel();
        try {
            RestTemplate restTemplate = new RestTemplate();
            if (symbol.endsWith("BTC")) {
                quoteModel.setMarket(Market.BTC);
            } else if (symbol.endsWith("ETH")) {
                quoteModel.setMarket(Market.ETH);
            }
            Binance binanceObj = restTemplate.getForObject(urlBuilder.toString(), Binance.class);
            quoteModel = QuotesPopulator.populateQuotes(quoteModel, binanceObj.getSymbol(), binanceObj.getLastPrice(),
                                                        binanceObj.getHighPrice(), binanceObj.getLowPrice(),
                                                        binanceObj.getPriceChangePercent(), binanceObj.getVolume());
            CacheManager.insert(CacheKey.BINANCE, quoteModel);
        } catch (Exception ex) {
            LOG.debug("[QuoteService][getQuoteFromBinance] Error getting data from binance", ex);
        }
        return CompletableFuture.completedFuture(Optional.ofNullable(quoteModel));
    }

    @Override
    @Async
    public CompletableFuture<Optional<QuoteModel>> getQuoteFromBittrex(String symbol)
    {
        if (StringUtils.isBlank(symbol)) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        boolean inCache = CacheManager.isInCache(CacheKey.BITTREX);
        if (inCache) {
            return CompletableFuture.completedFuture(Optional.ofNullable(CacheManager.getFromCache(CacheKey.BITTREX)));
        }
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AppConfig.getBittrexApi());
        QuoteModel quoteModel = new QuoteModel();
        try {
            symbol = SymbolMaker.bittrexSymbolMaker(symbol, quoteModel);
            urlBuilder.append(symbol);
            Bittrex bittrexObj = JacksonParser.parseJsonFromUrl(urlBuilder.toString(), Bittrex.class);
            Result[] data = bittrexObj.getResult();
            quoteModel = QuotesPopulator.populateQuotes(quoteModel, data[0].getMarketName()
                .split("-")[1], data[0].getLast(), data[0].getHigh(), data[0].getLow(),
                                                        calculateChangePercentage(data[0].getHigh(), data[0].getLow()),
                                                        data[0].getBaseVolume());
            
            CacheManager.insert(CacheKey.BITTREX, quoteModel);
        } catch (Exception ex) {
            LOG.debug("[QuoteService][getQuoteFromBittrex] Error getting data from bittrex", ex);
        }
        return CompletableFuture.completedFuture(Optional.ofNullable(quoteModel));
    }

    private String calculateChangePercentage(String high, String low)
    {
        return String
            .valueOf((Double.parseDouble(high) - Double.parseDouble(low))
                / Double.parseDouble(low) * 100);
    }

}
