package com.xmass.cloud.domain.trading.service;

import com.xmass.cloud.infrastructure.enums.RuleEnum;

public class MARuleStrategy implements RuleStrategy {

    private final IndicatorDTO pre;
    private final IndicatorDTO post;

    public MARuleStrategy(IndicatorDTO pre, IndicatorDTO post) {
        this.pre = pre;
        this.post = post;
    }

    @Override
    public RuleDTO estimate() {
        if(pre.getValue() > post.getValue() * 1.2) {
            return new RuleDTO(RuleEnum.BUY);
        }
        else if (pre.getValue() * 1.2 < post.getValue()) {
            return new RuleDTO(RuleEnum.SELL);
        }
        else {
            return new RuleDTO(RuleEnum.HOLD);
        }
    }

}
