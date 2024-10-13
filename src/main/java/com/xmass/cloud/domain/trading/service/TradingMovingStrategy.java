package com.xmass.cloud.domain.trading.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradingMovingStrategy implements TradingStrategy {

    @Override
    public TradingDTO calculate(List<Double> prices, int period) {
        double v = prices.stream()
                .skip(Math.max(0, prices.size() - period))
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return new TradingDTO(v);
    }

}
