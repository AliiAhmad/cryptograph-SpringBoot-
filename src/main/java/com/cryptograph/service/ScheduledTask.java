package com.cryptograph.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cryptograph.config.CacheKey;
import com.cryptogrpah.cm.CacheManager;

@Service
public class ScheduledTask
{

    private static final Logger LOG = LogManager.getLogger(ScheduledTask.class);

    // cron = "0 * * * * ?"
    // update every min
    @Scheduled( cron = "0/60 * * * * ?" )
    public void clearBinanceCache()
    {

        LOG.info("Running [updateBinanceCache] scheduled task -> updating binance cache");
        CacheManager.clear(CacheKey.BINANCE);
        LOG.info("Running [updateBinanceCache] scheduled task -> updated  binance cache");

    }

    @Scheduled( cron = "0/60 * * * * ?" )
    public void clearBittrexCache()
    {
        LOG.info("Running [updateBittrexCache] scheduled task -> updating bittrex cache");
        CacheManager.clear(CacheKey.BITTREX);
        LOG.info("Running [updateBittrexCache] scheduled task -> updated  bittrex cache");
    }

}
