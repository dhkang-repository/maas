package com.xmass.cloud.functional;

// 추상 메서드가 하나만 있으면, 함수형 인터페이스가 된다
// interface에 정의 가능한 형태가 다양함
// 아래와 같이 정의되어 있어도 함수형 인터페이스이다. (추상 메서드가 1개이기 때문이다)
@FunctionalInterface // java에서 제공하는 annotation -> 더 견고하게 interface를 관리 할 수 있다.
public interface RunSomething {
    void doIt(); // abstract가 생략되어 있다.

    // 인터페이스임에도 static method를 interface 내부에 정의가 가능하다.
    // public 도 생략이 가능하다.
    static void printName() {
        System.out.println("name");
    }

    // default method
    default void printAge() {
        System.out.println("age");
    }
}
