package com.ransibi.streampractice.mediumoperator;

import com.ransibi.streampractice.pojo.Author;
import com.ransibi.streampractice.pojo.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName &{NAME}
 * @Description 中间操作
 * @Author code-ran
 * @Date 2022/5/17 01:38
 * @Version1.0
 */
public class Demo {
    public static void main(String[] args) {
        //filter练习
        test();
        System.out.println("---------------");
        //map练习
        test01();
        System.out.println("---------------");
        //distinct练习
        test02();
        System.out.println("---------------");
        //sorted练习
        test03();

        System.out.println("---------------");
        //limit练习
        test04();

        System.out.println("---------------");
        //sort练习
        test05();

        System.out.println("---------------");
        //flatMap练习
        test06();

        System.out.println("---------------");
        //fltMap练习2
        test07();
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

    //filter练习： 打印所有姓名长度大于1的作家姓名
    private static void test() {
        List<Author> authorList = getAuthors();
        //1、常规函数式编程写法：
        authorList.stream().filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length() > 1;
            }
        }).forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author.getName());
            }
        });
        System.out.println("================");
        //2、使用lambda表达式的写法
        authorList.stream().filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    //map练习： 打印所有作家的姓名
    private static void test01() {
        List<Author> authorList = getAuthors();
        /**
         * 转换操作
         */
        //常规写法:传入的参数类型是Author(对象),返回的类型的String
        authorList.stream().map(new Function<Author, String>() {
            @Override
            public String apply(Author author) {
                return author.getName();
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println(name);
            }
        });

        //lambda式写法
        authorList.stream().map(author -> author.getName())
                .forEach(name -> System.out.println(name));

        System.out.println("-----------------");

        /**
         * 计算操作
         */
        //lambda写法
        authorList.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));


        System.out.println("-----------------");
        //函数式编程写法
        authorList.stream()
                .map(new Function<Author, Integer>() {
                    @Override
                    public Integer apply(Author author) {
                        return author.getAge();
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer age) {
                        return age + 10;
                    }
                })
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer age) {
                        System.out.println(age);
                    }
                });
    }

    //distinct练习:去除流中的重复元素
    private static void test02() {
        List<Author> authorList = getAuthors();
        //打印所有作家的姓名，并且要求其中不能有重复元素
        //lambda式写法
        authorList.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
        System.out.println("-----------------");
        //传统写法
        authorList.stream()
                .distinct()
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });
    }

    //sorted练习：对流中的元素进行排序
    private static void test03() {
        List<Author> authorList = getAuthors();
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素
        //(1)不带参数时 ---得在实体中重写Comparator接口
        //常规写法
        authorList.stream()
                .distinct()
                .sorted()
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getAge());
                    }
                });
        System.out.println("-----------------");
        //lambda写法
        authorList.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));

        System.out.println("-----------------");

        //（2）带参数时
        //常规写法
        authorList.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getAge());
                    }
                });
        System.out.println("-----------------");
        //lambda式写法
        authorList.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    //limit练习
    private static void test04() {
        List<Author> authorList = getAuthors();
        //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素，然后打印其中年龄最大的两个作家的名字。
        authorList.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .limit(2)
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });
    }

    //skip练习:跳过流中的前n个元素，返回剩下的元素
    private static void test05() {
        List<Author> authorList = getAuthors();
        //打印年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
        //传统方式
        authorList.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .skip(2)
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });
        System.out.println("===========");
        //lambda方式
        authorList.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .skip(2)
                .forEach(author -> System.out.println(author.getName()));
    }

    //flatMap练习： map只能把一个对象转换成另一个对象来作为流中的元素。而flatMap可以把一个对象转换成多个对象作为流中的元素
    private static void test06(){
        List<Author> authorList = getAuthors();
        //打印所有书籍的名字，要求对重复的元素进行去重
        //传统方式
        authorList.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBooks().stream();
                    }
                })
                .distinct()
                .forEach(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) {
                        System.out.println(book.getName());
                    }
                });
        System.out.println("---------------");
        //lambda表达式写法
        authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    //fltMap练习二:
    private static void test07(){
        List<Author> authorList = getAuthors();
        //打印现有数据的所有分类，要求对分类进行去重。不能出现这种格式： 哲学，爱情
        //传统写法
        authorList.stream()
                .flatMap(author ->author.getBooks().stream())
                .distinct()
                .flatMap(new Function<Book, Stream<?>>() {
                    @Override
                    public Stream<?> apply(Book book) {
                        return Arrays.stream(book.getCategory().split(","));
                    }
                })
                .distinct()
                .forEach(new Consumer<Object>() {
                    @Override
                    public void accept(Object category) {
                        System.out.println(category);
                    }
                });

        System.out.println("----------------");
        //lambda写法
        authorList.stream()
                .flatMap(author ->author.getBooks().stream())
                .distinct()
                .flatMap((Function<Book, Stream<?>>) book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));
    }


}

