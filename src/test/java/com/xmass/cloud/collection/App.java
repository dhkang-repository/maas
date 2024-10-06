package com.xmass.cloud.collection;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        names.forEach(System.out::println);
        Spliterator<String> spliterator = names.spliterator();
        while (spliterator.tryAdvance(System.out::println));

        // 절반 쪼개기
//        Spliterator<String> stringSpliterator = spliterator.trySplit();
//        while(stringSpliterator.tryAdvance(System.out::println));

        //
        long k = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("K"))
                .count();

        System.out.println(k);

        names.removeIf(s -> s.startsWith("k"));

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed().thenComparing(Comparator.reverseOrder()));


        names.stream().map(s-> {
            System.out.println(s + "1");
            return s.toUpperCase();
        }).map(s-> {
            System.out.println(s + "2");
            return s;
        }).collect(Collectors.toSet());
        OptionalInt optionalInt = OptionalInt.of(10);
        int asInt = optionalInt.getAsInt();

    }
}
