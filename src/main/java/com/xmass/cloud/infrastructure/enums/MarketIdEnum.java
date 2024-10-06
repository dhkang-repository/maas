package com.xmass.cloud.infrastructure.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

/**
 * @apiNote 아래 나열된 코인만 수용할 것
 */
@Getter
public enum MarketIdEnum {
    BTC("KRW-BTC", "비트코인"),
    ETH("KRW-ETH", "이더리움"),
    ETC("KRW-ETC", "이더리움클래식"),
    DOGE("KRW-DOGE", "도지코인"),
    SOL("KRW-SOL", "솔라나"),
    XRP("KRW-XRP", "리플")
    ;

    private String id;
    private String krName;

    MarketIdEnum(String id, String krName) {
        this.id = id;
        this.krName = krName;
    }

    @JsonCreator
    public static MarketIdEnum parse(String inputValue) {
        return Arrays.stream(values())
                .filter(v-> inputValue.toLowerCase().contains(v.getId().toLowerCase()))
                .findFirst().orElse(null);
    }
}
