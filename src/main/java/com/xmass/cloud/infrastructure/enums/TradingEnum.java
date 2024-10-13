package com.xmass.cloud.infrastructure.enums;

import com.xmass.cloud.domain.trading.service.IndicatorBollingerStrategy;
import com.xmass.cloud.domain.trading.service.IndicatorMovingStrategy;
import com.xmass.cloud.domain.trading.service.IndicatorRSIStrategy;
import com.xmass.cloud.domain.trading.service.IndicatorStrategy;
import lombok.Getter;

@Getter
public enum TradingEnum {
    MA(IndicatorMovingStrategy.class),
    RSI(IndicatorRSIStrategy.class),
    BLG(IndicatorBollingerStrategy.class)
    ;

    Class<? extends IndicatorStrategy> strategy;

    TradingEnum(Class<? extends IndicatorStrategy> strategy) {
        this.strategy = strategy;
    }


}
