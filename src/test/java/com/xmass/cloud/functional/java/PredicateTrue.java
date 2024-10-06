package com.xmass.cloud.functional.java;

import java.util.function.Predicate;

public class PredicateTrue implements Predicate<String> {

    @Override
    public boolean test(String ipt) {
        if (ipt.startsWith("a")) {
            return false;
        }

        return true;
    }
}
