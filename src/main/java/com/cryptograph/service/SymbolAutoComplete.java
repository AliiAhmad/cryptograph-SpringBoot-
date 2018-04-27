package com.cryptograph.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cryptograph.config.AppConfig;
import com.cryptograph.generated.models.CoinMarketCap;

@Service
public class SymbolAutoComplete implements ISymbolAutoComplete
{
    private final Map<String, String> symbolMap = new HashMap<>();
    private static final Logger LOG = LogManager.getLogger(SymbolAutoComplete.class);

    @Override
    public Map<String, String> getSymbols()
    {
        symbolMap.put("BTC", "Bitcoin");
        symbolMap.put("ETH", "Etherium");
        symbolMap.put("XRP", "Ripple");
        symbolMap.put("BCH", "Bitcoin cash");
        symbolMap.put("XLM", "Stellar Lumen");
        symbolMap.put("LTC", "Litecoin");
        symbolMap.put("TRX", "Tron");
        
        return symbolMap; 
       /*  if (symbolMap.isEmpty()) {
          try {
                RestTemplate restTemplate = new RestTemplate();
                ParameterizedTypeReference<List<CoinMarketCap>> responseType = new ParameterizedTypeReference<List<CoinMarketCap>>() {};
                ResponseEntity<List<CoinMarketCap>> resp = restTemplate.exchange(AppConfig.getCoinMarketCapApi(), HttpMethod.GET, null, responseType);
                // to do : check for status 200, if not populate from hard coded values
                if (resp.getStatusCode().value() == 200) {
                    List<CoinMarketCap> list = resp.getBody();
                    list.forEach(cms -> {
                        symbolMap.put(cms.getSymbol(), cms.getName());
                    });
                }
            } catch (Exception ex) {
                LOG.debug("[SymbolAutoComplete][getSymbols]Couldnot get resource for autocomplete", ex);
            }
        }

        return symbolMap;
       */
    }

}
