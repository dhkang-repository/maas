package com.xmass.cloud.infrastructure.enums;

import com.xmass.cloud.domain.trading.service.TradingBollingerStrategy;
import com.xmass.cloud.domain.trading.service.TradingMovingStrategy;
import com.xmass.cloud.domain.trading.service.TradingRSIStrategy;
import com.xmass.cloud.domain.trading.service.TradingStrategy;
import lombok.Getter;

@Getter
public enum TradingEnum {
    MA(TradingMovingStrategy.class),
    RSI(TradingRSIStrategy.class),
    BLG(TradingBollingerStrategy.class)
    ;

    Class<? extends TradingStrategy> strategy;

    TradingEnum(Class<? extends TradingStrategy> strategy) {
        this.strategy = strategy;
    }


}
