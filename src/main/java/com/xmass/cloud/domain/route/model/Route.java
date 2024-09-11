package com.xmass.cloud.domain.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Route {

    @JsonProperty("result_code")
    private String resultCode;
    @JsonProperty("result_msg")
    private String resultMsg;
    @JsonProperty("summary")
    private RouteSummary summary;



}
