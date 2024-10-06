package com.xmass.cloud.interfaces;

public class DefaultFoo implements Foo{

    @Override
    public void printName() {
        System.out.println("default Foo");
    }
}
