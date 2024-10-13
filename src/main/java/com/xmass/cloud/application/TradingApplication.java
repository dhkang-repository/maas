package com.xmass.cloud.application;

import com.xmass.cloud.domain.trading.service.TradingDTO;
import com.xmass.cloud.domain.trading.service.TradingStrategy;
import com.xmass.cloud.web.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TradingApplication {
    static final Logger LOGGER = LoggerFactory.getLogger(TradingApplication.class);


    private final Map<Class, TradingStrategy> strategyMap;

    public TradingApplication(List<TradingStrategy> strategies) {
        // 각 전략을 Map에 저장, 클래스 이름을 key로 사용
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(s -> s.getClass(), s -> s));
    }

    public TradingDTO calculate(Class<? extends TradingStrategy> policy, List<Double> prices, int period) {
        return this.strategyMap.get(policy).calculate(prices, period);
    }
}
