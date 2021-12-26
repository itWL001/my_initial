package org.initial.heart.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class SumTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p = new Person("张三", 20, '男');
        list.add(p);
        list.add(new Person("小丽", 20, '女'));
        list.add(new Person("小丽", 20, '男'));
        List<Integer> serialList = list.stream().mapToInt(new ToIntFunction<Person>() {
            @Override
            public int applyAsInt(Person d) {
                return d.getAge();
                //return Integer.parseInt(d.getAge().substring(3));
            }
        }).sorted().boxed().collect(Collectors.toList());
        System.out.println(serialList);
    }
}
