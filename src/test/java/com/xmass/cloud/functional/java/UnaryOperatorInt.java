package com.xmass.cloud.functional.java;

import java.util.function.UnaryOperator;

public class UnaryOperatorInt implements UnaryOperator<Integer> {
    @Override
    public Integer apply(Integer integer) {
        return 10 + integer;
    }
}
