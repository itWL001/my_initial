package org.initial.heart.stream;

import org.initial.heart.entity.Person;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {
    @Test
    public void testStreamToCollection() {
        List<String> one = new ArrayList<>();
        Collections.addAll(one, "赵信", "武则天", "虞姬", "项羽", "干将莫邪");
        System.out.println("one:" + one);
        //将流中的集合收集到集合中
        List<String> toList = one.stream().collect(Collectors.toList());
        System.out.println("toList:" + toList);
        Set<String> toSet = one.stream().collect(Collectors.toSet());
        System.out.println("toSet:" + toSet);
        //收集到指定的集合中
        ArrayList<String> arrList = one.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("arrList:" + arrList);
        HashSet<String> hashSet = one.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println("hashSet:" + hashSet);
        //将流中的集合收集到数组中
        String[] array = one.stream().toArray(String[]::new);
        System.out.println("array:" + array);
        System.out.println(Arrays.asList(array));
    }

    @Test
    public void testStreamToOther() {
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 18),
                new Person("王昭君", 21),
                new Person("西施", 19),
                new Person("杨玉环", 20));
        //获取最大值
//        Optional<Person> max = personStream.collect(Collectors.maxBy((s1, s2) -> s1.getAge() - s2.getAge()));
//        if (max.isPresent()){
//            System.out.println(max.get().getAge());
//        }
        //最小值
//        Optional<Person> min = personStream.collect(Collectors.minBy((s1, s2) -> s1.getAge() - s2.getAge()));
//        if (min.isPresent()){
//            System.out.println(min.get().getAge());
//        }
        //求和
//        Integer sum = personStream.collect(Collectors.summingInt(s -> s.getAge()));
//        System.out.println(sum);
        //平均值
//        Double avg = personStream.collect(Collectors.averagingInt(s -> s.getAge()));
//        System.out.println(avg);
        //统计数量
        Long count = personStream.collect(Collectors.counting());
        System.out.println(count);
    }

    @Test
    public void testStreamGroup() {
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 18),
                new Person("王昭君", 20),
                new Person("西施", 19),
                new Person("杨玉环", 20));
        //按年龄分组
//        Map<Integer, List<Person>> ageMap = personStream.collect(Collectors.groupingBy(s -> s.getAge()));
//        System.out.println(ageMap);
//        Map<Integer, List<Person>> ageMap2 = personStream.collect(Collectors.groupingBy(Person::getAge));
//        System.out.println(ageMap2);
        //按年龄大小
        Map<String, List<Person>> ageMap3 = personStream.collect(Collectors.groupingBy(e -> {
            if (e.getAge() > 19) {
                return "年轻";
            } else {
                return "年长";
            }
        }));
        System.out.println(ageMap3);
    }

    @Test
    public void testStreamGroup2() {
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 19),
                new Person("王昭君", 20),
                new Person("西施", 19),
                new Person("杨玉环", 20));
        //先根据年龄分组,在根据姓名分组
        //groupingBy(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)
//        Map<Integer, Map<String, List<Person>>> collect = personStream.collect(Collectors.groupingBy(Person::getAge,
//                Collectors.groupingBy(e -> {
//                    if (e.getAge() > 19) {
//                        return "年轻";
//                    } else {
//                        return "年长";
//                    }
//                })));
//        System.out.println("collect:" + collect);
        Map<Integer, Map<String, List<Person>>> collect1 = personStream.collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(Person::getName)));
        System.out.println(collect1);
        collect1.forEach((a, b) -> {
            b.forEach((c, d) -> {
                System.out.println(d.get(0).getAge() + d.get(0).getName());
            });
        });
//        {19={貂蝉=[Person(name=貂蝉, age=19)], 西施=[Person(name=西施, age=19)]}, 20={王昭君=[Person(name=王昭君, age=20)], 杨玉环=[Person(name=杨玉环, age=20)]}}
//        19貂蝉
//        19西施
//        20王昭君
//        20杨玉环
    }

    @Test
    //会根据是否为true,把集合分割成两个列表,一个true列表,一个false列表
    public void testStreamPartition() {
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 19),
                new Person("王昭君", 20),
                new Person("西施", 19),
                new Person("杨玉环", 20));
        Map<Boolean, List<Person>> collect = personStream.collect(Collectors.partitioningBy(e -> {
            return e.getAge() > 19;
        }));
        System.out.println("collect:" + collect);
        System.out.println(collect.get(true));
        System.out.println(collect.get(false));
    }

    @Test
    public void testJoin() {
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 19),
                new Person("王昭君", 20),
                new Person("西施", 19),
                new Person("杨玉环", 20));
        //根据一个字符串拼接  names:貂蝉_王昭君_西施_杨玉环
//        String names = personStream.map(Person::getName).collect(Collectors.joining("_"));
//        System.out.println("names:" + names);
        //根据三个字符串拼接,分隔符,前缀后缀 names:AA貂蝉_王昭君_西施_杨玉环BB
        String names = personStream.map(Person::getName).collect(Collectors.joining("_", "AA", "BB"));
        System.out.println("names:" + names);
    }

    @Test
    public void testParallelStream() {
        ArrayList<Object> list = new ArrayList<>();
        //直接获取并行的stream流
        Stream<Object> stream = list.parallelStream();
        //将串行流变成并行流
        Stream<Object> parallel = list.stream().parallel();

        long count = Stream.of(1, 4, 6, 7, 8, 9, 2, 3).parallel().filter(e -> {
            System.out.println(Thread.currentThread() + "::" + e);
            return e > 3;
        }).count();
        System.out.println(count);

    }

    //解决parallelStream线程安全问题
    @Test
    public void testParallelStreamNotice() {
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(0, 1000).parallel().forEach(e -> {
            list.add(e);
        });
        System.out.println("list:" + list.size());
        //解决parallelStream线程安全问题方案一:使用同步代码块
        Object obj = new Object();
        IntStream.rangeClosed(0, 1000).parallel().forEach(e -> {
            synchronized (obj) {
                list.add(e);
            }
        });
        //方案二:使用线程安全的集合
        //Vector<Integer> list2 = new Vector<>();
        Collection<Integer> synchronizedList = Collections.synchronizedCollection(list);
        IntStream.rangeClosed(0, 1000).parallel().forEach(e -> {
            synchronizedList.add(e);
        });
        //方案三:调用stream的collect/toArray 方法
        List<Integer> collect = IntStream.rangeClosed(0, 1000).parallel().boxed().collect(Collectors.toList());
    }


}


