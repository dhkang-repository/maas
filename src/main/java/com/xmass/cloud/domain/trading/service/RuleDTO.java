package com.xmass.cloud.domain.trading.service;

import com.xmass.cloud.infrastructure.enums.RuleEnum;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RuleDTO {
    RuleEnum rule;

    public RuleDTO(RuleEnum rule) {
        this.rule = rule;
    }
}
