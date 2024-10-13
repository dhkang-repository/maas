package com.xmass.cloud.domain.trading.service;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TradingDTO {
    private double value;
    private double[] values;

    public TradingDTO(double value) {
        this.value = value;
    }

    public TradingDTO(double[] values) {
        this.values = values;
    }
}
