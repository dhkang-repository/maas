package com.xmass.cloud.domain.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bid {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("min_total")
    private String minTotal;
}
