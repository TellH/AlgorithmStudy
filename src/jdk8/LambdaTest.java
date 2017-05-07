package jdk8;

/**
 * Created by tlh on 2017/5/7.
 */
public class LambdaTest {
    class Foo {
        Integer getFirst(String s) {
            return Integer.valueOf(s.charAt(0));
        }
    }

    public void simpleTest() {
        // 内部类的形式
        Converter<String, Integer> converter = new Converter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        converter = (from) -> Integer.valueOf(from);
        converter = Integer::valueOf; // 通过::关键字获取方法
        converter = Integer::new; // 通过::关键字获取构造函数
        Foo foo = new Foo();
        converter = foo::getFirst;

        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }

    public void finalVariableTest() {
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
//        num = 3; // 不能编译，num被隐式声明成final了
    }


}
