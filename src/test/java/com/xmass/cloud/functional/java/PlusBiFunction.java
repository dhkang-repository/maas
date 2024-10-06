package com.xmass.cloud.functional.java;

import java.util.function.BiFunction;

public class PlusBiFunction implements BiFunction<Integer, Integer, Integer> {
    @Override
    public Integer apply(Integer integer, Integer integer2) {
        return integer + integer2;
    }
}
