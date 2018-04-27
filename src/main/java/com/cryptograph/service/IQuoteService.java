package com.cryptograph.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.cryptograph.generated.models.QuoteModel;

public interface IQuoteService
{

    CompletableFuture<Optional<QuoteModel>> getQuoteFromBinance(String symbol);

    CompletableFuture<Optional<QuoteModel>> getQuoteFromBittrex(String symbol);

}
