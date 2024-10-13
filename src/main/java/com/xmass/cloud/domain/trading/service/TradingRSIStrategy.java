package com.xmass.cloud.domain.trading.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradingRSIStrategy implements TradingStrategy {

    @Override
    public TradingDTO calculate(List<Double> prices, int period) {
        double gain = 0, loss = 0;
        for (int i = 1; i < prices.size(); i++) {
            double change = prices.get(i) - prices.get(i - 1);
            if (change > 0) gain += change;
            else loss -= change;
        }

        double rs = gain / Math.max(loss, 1e-10);
        return new TradingDTO(100 - (100 / (1 + rs)));
    }
    
}
