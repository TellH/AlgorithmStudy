package jdk8;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by tlh on 2017/5/7.
 */
public class FunctionInterface {
    /**
     * Predicates
     * Predicate是一个布尔类型的函数，该函数只有一个输入参数。Predicate接口包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）
     */
    public void predicates() {
        Predicate<String> predicate = s -> s.length() > 0;
        System.out.println(predicate.test("foo")); // true
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /**
     * Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compose, andThen）
     */
    public void Functions() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");     // 123
    }

    /**
     * Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
     */
    public void Suppliers() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
    }

    private class Person {
        String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }
    }

    /**
     * 输入一个参数，没有返回值
     */
    public void Consumers() {
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.name);
        greeter.accept(new Person("Luke"));
    }


}
