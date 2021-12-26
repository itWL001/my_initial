package org.initial.heart.stream;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(of = "name")
@EqualsAndHashCode(of = {"name","age"})
public class Person {
    private String name;
    private Integer age;
    private char sex;

    public static void main(String[] args) {
        // 1.构造 List
        List<Person> list = new ArrayList<>();
        Person p = new Person("张三", 20, '男');
        list.add(p);
        list.add(new Person("小丽", 20, '女'));
        list.add(new Person("小丽", 20, '男'));
        // 2.去重
        //Set<Person> hashSet = new HashSet<>(list);
        //List<Person> newList = new ArrayList<>(hashSet);
        //System.out.println(newList);//没有对“小丽”去重
        //方式2
        //List<Person> collect = list.stream().distinct().collect(Collectors.toList());
        //System.out.println(collect);
        //方式3
        List<Person> newList = new ArrayList<>();

        newList = list.stream()
                .filter(distinctByKey(o -> o.getName() + ";" + o.getAge()))
                .collect(Collectors.toList());
        System.out.println(newList);
        Map<String, Person> collect1 = list.stream().collect
                (Collectors.toMap(per -> per.getAge().toString() + per.getAge(), Function.identity(), (a, b) -> a));


        List collect = list.stream().map(Person::getAge).collect(Collectors.toList());
        //
        System.out.println("----------------------");
        System.out.println(list);
        List<Person> collect2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect2);


    }

    /**
     * 自定义的去重方法
     *
     * @param <T>          待去重实体
     * @param keyExtractor 去重标记(如：o.getName() + ";" + o.getAge())
     * @return
     */
    //    Stream<T> filter(Predicate<? super T> predicate);
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}


