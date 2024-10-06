package com.xmass.cloud.domain.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmass.cloud.domain.account.model.Account;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Market {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("order_types")
    private List<String> orderTypes;
    @JsonProperty("order_sides")
    private List<String> orderSides;
    @JsonProperty("bid_types")
    private List<String> bidTypes;
    @JsonProperty("ask_types")
    private List<String> askTypes;

    @JsonProperty("bid")
    private Bid bid;
    @JsonProperty("ask")
    private Ask ask;

    @JsonProperty("max_total")
    private String maxTotal;
    @JsonProperty("state")
    private String state;

    @JsonProperty("bid_account")
    private Account bidAccount;
    @JsonProperty("ask_account")
    private Account askAccount;
}
