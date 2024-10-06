package com.xmass.cloud.functional.java;

import java.util.function.Consumer;

public class PrinterConsumer implements Consumer<Integer> {
    @Override
    public void accept(Integer integer) {
        System.out.println(integer);
    }
}
