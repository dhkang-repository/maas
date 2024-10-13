package com.xmass.cloud.domain.trading.service;

import java.util.List;

public interface TradingStrategy {
    TradingDTO calculate(List<Double> prices, int period);
}

