package com.ransibi.lambdapractice;

import java.util.function.Function;

/**
 * @ClassName &{NAME}
 * @Description Demo04
 * @Author code-ran
 * @Date 2022/5/16 17:07
 * @Version1.0
 */
public class Demo04 {
    public static void main(String[] args) {
//        Integer result = typeConver(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        });
        //转换成lambda表达式
        Integer result = typeConver(s -> Integer.valueOf(s));

        System.out.println(result);

    }
    //方法泛型
    public static <R> R typeConver(Function<String, R> function) {
        String str = "12345";
        R result = function.apply(str);
        return result;
    }
}
