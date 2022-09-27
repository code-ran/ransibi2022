package com.ransibi.lambdapractice;

import java.util.function.IntConsumer;

/**
 * @ClassName &{NAME}
 * @Description Demo05
 * @Author code-ran
 * @Date 2022/5/16 17:18
 * @Version1.0
 */
public class Demo05 {
    public static void main(String[] args) {
//        foreachArr(new IntConsumer() {
//            @Override
//            public void accept(int value) {
//                System.out.println(value);
//            }
//        });

        //lambda写法：其实就是对你们内部类的优化
        foreachArr(value ->
                System.out.println(value)
        );
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }
}
