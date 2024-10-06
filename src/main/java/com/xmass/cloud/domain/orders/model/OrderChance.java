package com.xmass.cloud.domain.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderChance {
    @JsonProperty("bid_fee")
    private String bidFee; // 매수 수수료 비율 NumberString
    @JsonProperty("ask_fee")
    private String askFee;	// 매도 수수료 비율	NumberString
    @JsonProperty("market")
    private Market market;
}
