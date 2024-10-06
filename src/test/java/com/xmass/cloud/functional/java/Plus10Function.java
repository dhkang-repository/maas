package com.xmass.cloud.functional.java;

import java.util.function.Function;

public class Plus10Function implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }

    public static void main(String[] args) {
        Plus10Function plus10Function = new Plus10Function();
        Integer apply = plus10Function.apply(10);
        System.out.println(apply);

        Function<Integer, Integer> multiply2 = (i) -> 2 * i;

        // multiply2 이후 plus10을 실행한다.
        Function<Integer, Integer> compose = plus10Function.compose(multiply2);

        Integer composeResult = compose.apply(10);
        System.out.println(composeResult);

        Function<Integer, Integer> andThen = plus10Function.andThen(multiply2);
        Integer applyResult = andThen.apply(10);
        System.out.println(applyResult);
    }
}
