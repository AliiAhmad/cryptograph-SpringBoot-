package com.cryptograph.config;

public class AppConfig
{
    private final static String COIN_MARKET_CAP_API = "https://api.coinmarketcap.com/v1/ticker/?start=0&limit=10";
    private final static String BINANCE_API = "https://api.binance.com/api/v1/ticker/24hr?symbol=";
    private final static String BITTREX_API = "https://bittrex.com/api/v1.1/public/getmarketsummary?market=";


    public static String getCoinMarketCapApi()
    {
        return COIN_MARKET_CAP_API;
    }

    public static String getBinanceApi()
    {
        return BINANCE_API;
    }
    
    public static String getBittrexApi()
    {
        return BITTREX_API;
    }

}
