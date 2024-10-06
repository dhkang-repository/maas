package com.xmass.cloud.interfaces;

public interface Foo {
    void printName();

    default void printNameUpperCase() {
        System.out.println("FOO");
    }
}
