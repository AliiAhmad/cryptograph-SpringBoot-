package com.cryptograph.utils;

import com.cryptograph.generated.models.QuoteModel;

public class QuotesPopulator
{
    public static QuoteModel populateQuotes(QuoteModel quoteModel, String marketName, String lastPrice, String high,
        String low,
        String changePer,
        String vol)
    {
        quoteModel.setTicker(marketName);
        quoteModel.setPrice(lastPrice);
        quoteModel.setHigh24hr(high);
        quoteModel.setLow24h(low);
        quoteModel.setChange24h(changePer);
        quoteModel.setVolume(vol);
        return quoteModel;

    }
}
