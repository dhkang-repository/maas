package com.xmass.cloud.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;

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

    public HashMap<String, String> getParam() {
        HashMap<String, String> returnVal = new HashMap<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for(Field field : declaredFields) {
            Object o = null;
            try {
                o = field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if(o != null) {
                returnVal.put(field.getName(), (String) o);
            }
        }
        return returnVal;
    }
}
