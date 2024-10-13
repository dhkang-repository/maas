package com.xmass.cloud.domain.trading.service;

import java.util.List;

public interface IndicatorStrategy {
    IndicatorDTO calculate(List<Double> prices, int period);
}

