package com.xmass.cloud.domain.account.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("balance")
    private BigDecimal balance;
    @JsonProperty("locked")
    private String locked;
    @JsonProperty("avg_buy_price")
    private String avgBuyPrice;
    @JsonProperty("avc_buy_price_modified")
    private Boolean avcBuyPriceModified;
    @JsonProperty("unit_currency")
    private String unitCurrency;
}
