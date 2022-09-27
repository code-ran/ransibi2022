package com.ransibi.streampractice;
import com.ransibi.streampractice.pojo.Author;
import com.ransibi.streampractice.pojo.Book;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName &{NAME}
 * @Description StreamDemo
 * @Author code-ran
 * @Date 2022/5/16 18:00
 * @Version1.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        test02();
        test03();
        List<Author> lst = getAuthors();
//        System.out.println(lst);
        /**
         * 需求：打印所有年龄小于18的作家的名字，并且需要去重
         */
        //-----方式一：传统方式
        //把集合转换成流:distinct()去重，filter()数据过滤，forEach()循环操作
        lst.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() < 18;
                    }
                }).forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });

        //---方式二：写成lambda的样式
        lst.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "张三", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "李四", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "王五", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "王五", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

    //对数组进行操作
    private static void test02() {
        Integer[] arr = {1, 3, 4, 5};
        //把数组转换成流
        //方式一：使用Arrays.stream()方法
//        Stream<Integer> stream = Arrays.stream(arr);
        //方式二：使用Stream.of()方法
        Stream<Integer> stream = Stream.of(arr);
        stream.distinct()
                .filter(integer -> integer > 2)
                .forEach(integer -> System.out.println(integer));
    }

    //对map进行操作
    private static void test03() {
        Map<String, Integer> map = new HashMap<>();
        map.put("蜡笔小新", 19);
        map.put("懒羊羊", 18);
        map.put("tsk", 28);
        //获取单列集合entrySet
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        //将单列集合转换成流
        Stream<Map.Entry<String, Integer>> entryStream = entrySet.stream();
        entryStream.filter(stringIntegerEntry -> stringIntegerEntry.getValue() > 18)
                .forEach(stringIntegerEntry -> System.out.println(stringIntegerEntry.getKey() + "===" + stringIntegerEntry.getValue()));
    }
}
