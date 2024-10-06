package com.xmass.cloud.functional.java;

import java.util.function.Supplier;

public class NumberSupplier implements Supplier<Integer> {
    @Override
    public Integer get() {
        return 10;
    }
}
