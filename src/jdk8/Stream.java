package jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2017/5/7.
 * Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。
 */
public class Stream {
    List<String> collection = new ArrayList<>();

    {
        collection.add("ddd2");
        collection.add("aaa2");
        collection.add("bbb1");
        collection.add("aaa1");
        collection.add("bbb3");
        collection.add("ccc");
        collection.add("bbb2");
        collection.add("ddd1");
    }

    public void Filter() {
        collection.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
    }

    /**
     * sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的。
     */
    public void Sorted() {
        collection.stream()
                .filter(s -> s.startsWith("a"))
                .sorted()
                .forEach(System.out::println);
    }

    public void Map() {
        collection.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * 流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行。
     */
    public void parallel() {
        long count = collection.parallelStream().sorted().count();
    }


}
