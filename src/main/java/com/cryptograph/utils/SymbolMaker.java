package com.cryptograph.utils;

import com.cryptograph.config.Market;
import com.cryptograph.generated.models.QuoteModel;

public class SymbolMaker
{
    public static String bittrexSymbolMaker(String symbol, QuoteModel quoteModel)
    {
        if (symbol.endsWith("BTC")) {
            quoteModel.setMarket(Market.BTC);
            symbol = "BTC" + "-" + symbol.substring(0, symbol.indexOf("BTC"));
        } else if (symbol.endsWith("ETH")) {
            quoteModel.setMarket(Market.ETH);
            symbol = "ETH" + "-" + symbol.substring(0, symbol.indexOf("ETH"));
        } else if (symbol.endsWith("USDT")) {
            quoteModel.setMarket(Market.USDT);
            symbol = "USDT" + "-" + symbol.substring(0, symbol.indexOf("USDT"));
        }
        return symbol;
    }
}
