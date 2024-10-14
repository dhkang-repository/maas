package com.xmass.cloud.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UpbitResponse {
    @JsonProperty("code")
    private String market;
    @JsonProperty("trade_price")
    private double tradePrice;
}