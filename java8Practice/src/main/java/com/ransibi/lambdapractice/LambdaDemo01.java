package com.ransibi.lambdapractice;

/**
 * @ClassName &{NAME}
 * @Description LambdaDemo01
 * @Author code-ran
 * @Date 2022/5/16 16:36
 * @Version1.0
 */
// TODO: 2022/5/16函数式编程关注的是：具体的参数列表和方法体
public class LambdaDemo01 {
    public static void main(String[] args) {
        //匿名内部类的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程中run方法被执行了");
            }
        }).start();
        //使用lambda的写法
        new Thread(() -> System.out.println("新线程中run方法被执行了")).start();
    }
}
