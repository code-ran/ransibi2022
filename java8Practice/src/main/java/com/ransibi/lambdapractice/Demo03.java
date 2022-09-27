package com.ransibi.lambdapractice;

import java.util.function.IntPredicate;

/**
 * @ClassName &{NAME}
 * @Description Demo03
 * @Author code-ran
 * @Date 2022/5/16 16:59
 * @Version1.0
 */
public class Demo03 {
    public static void main(String[] args) {
        //匿名内部类
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value%2 == 0;
            }
        });
        //写成lambda方式
        printNum(value -> value%2 == 0);
    }
    //IntPredicate 接口中只有一个test的抽象方法，可以采用匿名内部类的写法
    public static void printNum(IntPredicate predicate){
        int [] arr ={1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if (predicate.test(i)){
                System.out.println(i);
            }
        }

    }
}
