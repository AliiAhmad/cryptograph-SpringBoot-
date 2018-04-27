package com.cryptogrpah.cm;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.cryptograph.config.CacheKey;
import com.cryptograph.generated.models.QuoteModel;

@Component
public class CacheManager
{
    private static Map<String, QuoteModel> binanceMap = new HashMap<>();
    private static Map<String, QuoteModel> bittrexMap = new HashMap<>();

    public static void insert(String key, QuoteModel model)
    {
        if (CacheKey.BINANCE.equals(key))
            binanceMap.put(key, model);
        if (CacheKey.BITTREX.equals(key))
            bittrexMap.put(key, model);
    }

    public static void clear(String key)
    {
        if (CacheKey.BINANCE.equals(key))
            binanceMap.clear();
        if (CacheKey.BITTREX.equals(key))
            bittrexMap.clear();
    }

    public static boolean isInCache(String key)
    {
        if (CacheKey.BINANCE.equals(key))
            return binanceMap.containsKey(key);
        if (CacheKey.BITTREX.equals(key))
            return bittrexMap.containsKey(key);
        return false;

    }

    public static QuoteModel getFromCache(String key)
    {
        if (CacheKey.BINANCE.equals(key))
            return binanceMap.get(key);
        if (CacheKey.BITTREX.equals(key))
            return bittrexMap.get(key);

        return null;

    }

}
