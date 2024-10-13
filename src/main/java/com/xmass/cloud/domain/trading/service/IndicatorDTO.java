package com.xmass.cloud.domain.trading.service;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class IndicatorDTO {
    private double value;
    private double[] values;

    public IndicatorDTO(double value) {
        this.value = value;
    }

    public IndicatorDTO(double[] values) {
        this.values = values;
    }
}
