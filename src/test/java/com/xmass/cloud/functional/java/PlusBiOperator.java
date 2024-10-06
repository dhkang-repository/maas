package com.xmass.cloud.functional.java;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class PlusBiOperator implements BinaryOperator<Integer> {

    @Override
    public Integer apply(Integer object, Integer object2) {
        return object + object2;
    }

    private void run() {
        // 사실상 final (effective final이라고도 함)
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // print 11
            }
        }

        // 익명 클래스
        Consumer<Integer> consumer = new Consumer<>() {

            @Override
            public void accept(Integer baseNumber) { // input 12
                System.out.println(baseNumber); // print 12
            }

        };

        // 람다
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);

        // shadowing
        // -> 지역 변수가 더 상위에 있는 값을 가린다. (scope 밖에 있는 값보다 우선순위를 가진다)
        // 로컬 클래스(또는 neted class)와 익명 클래스는 shadowing이 된다.
        // 람다는 쉐도윙이 안된다. (-> 람다는 scope 이 따로 없다)
        // 람다는 baseNumber 가 최초로 정의된 scope과 같은 scope을 가진다.
    }

}
