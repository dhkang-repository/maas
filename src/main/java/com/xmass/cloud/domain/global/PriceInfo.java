package com.xmass.cloud.domain.global;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class PriceInfo {
    private final ConcurrentLinkedQueue<Double> prices = new ConcurrentLinkedQueue<>();

    public void add(Double price) {
        if(prices.size()>1000) {
            prices.remove();
            prices.add(price);
        } else {
            prices.add(price);
        }
    }

    public List<Double> getPrices() {
        return prices.stream().toList();
    }
}
