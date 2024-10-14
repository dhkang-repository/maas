package com.xmass.cloud.application;

import com.xmass.cloud.domain.trading.service.IndicatorDTO;
import com.xmass.cloud.domain.trading.service.IndicatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IndicatorApplication {
    static final Logger LOGGER = LoggerFactory.getLogger(IndicatorApplication.class);


    private final Map<Class, IndicatorStrategy> strategyMap;

    public IndicatorApplication(List<IndicatorStrategy> strategies) {
        // 각 전략을 Map에 저장, 클래스 이름을 key로 사용
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(s -> s.getClass(), s -> s));
    }

    public IndicatorDTO calculate(Class<? extends IndicatorStrategy> policy, List<Double> prices, int period) {
        return this.strategyMap.get(policy).calculate(prices, period);
    }
}
