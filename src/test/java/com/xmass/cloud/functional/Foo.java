package com.xmass.cloud.functional;

public class Foo {

    public static void main(String[] args) {

        // 익명 내부 클래스 Anonymous inner class
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // lambda expression (-> 결국 object 이다 -> param, return 가능)
        RunSomething runSomethingLambda = () -> System.out.println("Hello");
        RunSomething runSomethingLambda2 = () -> {
            System.out.println("Hello");
            System.out.println("Hello2");
        };

        //

        runSomethingLambda2.doIt();

        RunSomething2 runSomething2 = (number) -> number + 10;

        // 같은 input 은 같은 output 을 나타내야, 함수형 프로그래밍이라고 말할 수 있다
        // 내부에 상태값을 가지거나 외부 값을 변경하려는 경우, 순수 함수형 프로그래이라고 말 할 수 없다.
        System.out.println(runSomething2.doIt(1));
        System.out.println(runSomething2.doIt(1));
        System.out.println(runSomething2.doIt(1));

        // https://bugoverdose.github.io/development/lambda-capturing-and-free-variable/
        // 함수형 프로그래밍에서 유의해야 될 점은 위 링크를 참조하자.
    }
}
