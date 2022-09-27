package com.ransibi.streampractice.endoperation;

import com.ransibi.streampractice.pojo.Author;
import com.ransibi.streampractice.pojo.Book;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName &{NAME}
 * @Description Demo
 * @Author code-ran
 * @Date 2022/5/17 01:38
 * @Version1.0
 */
public class Demo {
    public static void main(String[] args) {
        //forEach()练习
        test01();

        System.out.println("-------------");
        //count()练习
        test02();

        System.out.println("-------------");
        //max&&min练习
        test03();
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "张三", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "李四", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "王", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "王", 14, "是这个世界在限制他的思维", null);

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

    //forEach练习:对流中的元素进行遍历操作，我们通过传入的参数去指定对遍历到的元素进行什么具体操作。
    private static void test01() {
        List<Author> authorList = getAuthors();
        //输出所有作家的名字
        authorList.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name -> System.out.println(name));
    }

    //count练习：可以用来获取当前流中元素的个数。
    private static void test02() {
        //打印这些作家的所出书籍的数目，注意删除重复元素
        List<Author> authors = getAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }

    //max&&min练习：
    private static void test03() {
        //分别获取这些作家的所出书籍的最高分和最低分并打印。
        //Stream<Author>  -> Stream<Book> ->Stream<Integer>  ->求值
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(max.get());
        System.out.println(min.get());
    }

    //collect练习1：
    private static void test04() {
        //(1)获取一个存放所有作者名字的List集合。
        List<Author> authors = getAuthors();
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);

    }

    //collect练习2：
    private static void test05() {
        //获取一个所有书名的Set集合。
        //        获取一个所有书名的Set集合。
        List<Author> authors = getAuthors();
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());

        System.out.println(books);

    }

    //collect练习3：
    private static void test06() {
        //获取一个Map集合，map的key为作者名，value为List<Book>
        //        获取一个Map集合，map的key为作者名，value为List<Book>
        List<Author> authors = getAuthors();
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(map);

    }

}
