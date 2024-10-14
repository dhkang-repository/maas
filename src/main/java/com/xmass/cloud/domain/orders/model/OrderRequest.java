package com.xmass.cloud.domain.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmass.cloud.infrastructure.event.UpbitResponse;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

@Builder
@Data
public class OrderRequest {
    @JsonProperty("market")
    String market;
    @JsonProperty("side")
    String side;
    @JsonProperty("volume")
    String volume;
    @JsonProperty("price")
    String price;
    @JsonProperty("ord_type")
    String ordType;
    @JsonProperty("identifier")
    String identifier;
    @JsonProperty("time_in_force")
    String timeInForce;

    public HashMap<String, String> param() {
        HashMap<String, String> returnVal = new HashMap<>();
        if(Objects.nonNull(market) && !market.isEmpty()) {
            returnVal.put("market", market);
        }
        if(Objects.nonNull(side) && !side.isEmpty()) {
            returnVal.put("side", side);
        }
        if(Objects.nonNull(price) && !price.isEmpty()) {
            returnVal.put("price", price);
        }
        if(Objects.nonNull(volume) && !volume.isEmpty()) {
            returnVal.put("volume", volume);
        }
        if(Objects.nonNull(ordType) && !ordType.isEmpty()) {
            returnVal.put("ordType", ordType);
        }
        return returnVal;
    }
}
