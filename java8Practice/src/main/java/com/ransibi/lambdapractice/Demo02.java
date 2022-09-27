package com.ransibi.lambdapractice;

import java.util.function.IntBinaryOperator;

/**
 * @ClassName &{NAME}
 * @Description Demo02
 * @Author code-ran
 * @Date 2022/5/16 16:54
 * @Version1.0
 */
public class Demo02 {
    public static void main(String[] args) {
        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        //使用使用lambda的写法
//        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);
    }
    //IntBinaryOperator是一个接口，并且只有一个抽象方法。
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }
}
