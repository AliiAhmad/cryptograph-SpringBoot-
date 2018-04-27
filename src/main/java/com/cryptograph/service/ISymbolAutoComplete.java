package com.cryptograph.service;

import java.util.Map;

@FunctionalInterface
public interface ISymbolAutoComplete
{
    Map<String, String> getSymbols();
}
