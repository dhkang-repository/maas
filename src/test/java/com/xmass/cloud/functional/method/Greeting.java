package com.xmass.cloud.functional.method;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Greeting {

    private String data;
    public Greeting() {
    }

    public Greeting(String data) {
        this.data = data;
    }

    public static String hi(String data) {
        System.out.println(data);
        return data;
    }

    public String hello(String hello) {
        return hello;
    }

    public static void main(String[] args) {
        // :: -> method reference
        UnaryOperator<String> hi = Greeting::hi;

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello =greeting::hello;
        String helloResult = hello.apply("hello");

        // 생성자를 함수로 받는다
        Supplier<Greeting> newGreeting = Greeting::new;
        Greeting newGreetingInstance = newGreeting.get();

        // param constructor
        Function<String, Greeting> newGreetingFunc = Greeting::new;
        Greeting newGreetingInstance2 = newGreetingFunc.apply("data");

        //
        String[] names = {"keesun", "whiteship", "toby"};

//        Arrays.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });

        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));

    }
}
