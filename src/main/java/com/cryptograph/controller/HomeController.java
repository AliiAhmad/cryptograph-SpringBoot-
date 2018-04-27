package com.cryptograph.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cryptograph.exceptions.ResourceNotFoundException;
import com.cryptograph.generated.models.QuoteModel;
import com.cryptograph.service.IQuoteService;
import com.cryptograph.service.ISymbolAutoComplete;

@Controller
public class HomeController
{
    private final ISymbolAutoComplete symbolAutoComplete;
    private final IQuoteService quoteService;
    private static final Logger LOG = LogManager.getLogger(HomeController.class);

    public HomeController(ISymbolAutoComplete symbolAutoComplete, IQuoteService quoteService)
    {
        this.symbolAutoComplete = symbolAutoComplete;
        this.quoteService = quoteService;
    }

    @GetMapping( "/" )
    public String home()
    {
        return "cg.index";
    }

    @RequestMapping( value = "/symbols", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getSymbols()
    {
        Map<String, String> autoCompleteMap = Collections.emptyMap();
        autoCompleteMap = this.symbolAutoComplete.getSymbols();
        if (autoCompleteMap.isEmpty()) {

            throw new ResourceNotFoundException("Resource Not Found");
        }
        return new ResponseEntity<>(autoCompleteMap, HttpStatus.OK);

    }

    @RequestMapping( value = "/getQuotes/{symbol}", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getQuotes(@PathVariable( "symbol" ) String symbol)
    {
        Map<String, Map<String, QuoteModel>> quoteModelMap = new HashMap<>();

        Map<String, QuoteModel> q = new HashMap<>();
        long start = System.currentTimeMillis();

        LOG.info("Requesting for the quote");
        try {
            CompletableFuture<Optional<QuoteModel>> qmBinance = this.quoteService.getQuoteFromBinance(symbol); //e.g TRXBTC
            CompletableFuture<Optional<QuoteModel>> qmBittrex = this.quoteService.getQuoteFromBittrex(symbol);
            CompletableFuture.allOf(qmBinance, qmBittrex).join();

            LOG.info("Received the quote, total time taken:" + (System.currentTimeMillis() - start) + " ms");

            if (!qmBinance.get().isPresent() && !qmBittrex.get().isPresent()) {
                throw new ResourceNotFoundException("Resource Not Found");
            }
            q.put("binance", qmBinance.get().get());
            q.put("bittrex", qmBittrex.get().get());

        } catch (Exception ex) {
            LOG.debug("[HomeController][getQuotes]Error getting data from remote service", ex);
        }

        quoteModelMap.put("market", q);

        return new ResponseEntity<>(quoteModelMap, HttpStatus.OK);

    }

}
