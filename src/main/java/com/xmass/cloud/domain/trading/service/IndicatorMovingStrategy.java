package com.xmass.cloud.domain.trading.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndicatorMovingStrategy implements IndicatorStrategy {

    @Override
    public IndicatorDTO calculate(List<Double> prices, int period) {
        double v = prices.stream()
                .skip(Math.max(0, prices.size() - period))
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return new IndicatorDTO(v);
    }

}
