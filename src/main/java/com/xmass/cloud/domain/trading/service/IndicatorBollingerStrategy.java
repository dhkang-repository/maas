package com.xmass.cloud.domain.trading.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndicatorBollingerStrategy implements IndicatorStrategy {

    @Override
    public IndicatorDTO calculate(List<Double> prices, int period) {
        double ma = prices.stream()
                .skip(Math.max(0, prices.size() - period))
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        double variance = prices.stream()
                .skip(Math.max(0, prices.size() - period))
                .mapToDouble(p -> Math.pow(p - ma, 2))
                .sum() / period;

        double stdDev = Math.sqrt(variance);

        return new IndicatorDTO(new double[] {ma + 2 * stdDev, ma - 2 * stdDev});
    }
    
}
