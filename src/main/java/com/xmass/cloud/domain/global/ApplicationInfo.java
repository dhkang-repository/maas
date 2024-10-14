package com.xmass.cloud.domain.global;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationInfo {
    private String target;

    public String getSocketRequest() {
        return "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-"+target+"\"]}]";
    }
}
