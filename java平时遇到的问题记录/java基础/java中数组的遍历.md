###               java中对数组的操作：

1、常用for循环：

```java
public class Demo{
    public static void main(String args[]){
        int [] a = {1,2,3,4,5};
        for(int i = 0;i<a.length;i++){
            System.out.print(a[i]);
            
        }
    }
}
```



2、for each方式：

```java
public class Demo{
    public static void main(String args[]){
      int [] b = {2,4,6,8,0};
      for(int i : b){
      System.out.print(i);
      }
            
        }
    }

```





两种方式的比较：

​        第一种方式是通过数组的索引来遍历数组的，而第二种方式是直接拿到数组中的元素进行输出。二者各有优点，使用场景看具体需求。



3、数组元素内容的打印：

​             java中提供了Arrays.toString();方法可以很方便的打印出数组元素的内容。也可以通过传统的方式进行打印。

```java
public class Demo{
    public static void main(String args[]){
        int [] c = {6,7,4,2,9};
        System.out.print(Arrays.toString(c));
    }
}
```



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



4、数组的排序：

（1）冒泡排序：使数组按升序输出

<span style=color:red> 注意</span>：

该数组的输出仍然使用Arrays.toString();方法。

```java
package com.zexin.ransibi;

import java.util.Arrays;

/**
 * @author rasibi
 * @create 2021-07-14 10:03
 */
public class Maopao {
    public static void main(String[] args) {
        int [] a = {2,6,3,9,1,0,4};
        for(int i = 0;i<a.length-1;i++){
            for (int j = 0; j <a.length-1-i ; j++) {
                if(a[j]>a[j+1]){
                    int temp = 0;
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }

            }
        }
        System.out.println(Arrays.toString(a));

    }
}

```



5、实际上，Java的标准库已经内置了排序功能，我们只需要调用JDK提供的`Arrays.sort()`就可以排序

```java
package com.zexin.ransibi;
import java.util.Arrays;

/**
 * @author rasibi
 * @create 2021-07-14 10:18
 */
public class Sort {

    public static void main(String args[]) {
        int[] d = {3, 7, 2, 0, 1, 6, 5};
        //java内置的排序方法
        Arrays.sort(d);
        System.out.print(Arrays.toString(d));
    }
}


```



6、二维数组：

就是在一维数组中再嵌套一层一维数组：

​      打印二维数组，可以使用java中内置的Arrays.deepToString();方法。

```java
package com.zexin.ransibi;

import java.util.Arrays;

/**
 * @author rasibi
 * @create 2021-07-14 10:31
 */
public class TwoArray {

        public static void main(String args[]){
            int [][] e = {{1,2,3},{4,5,6},{7,8,9}};
           /* for(int i=0;i<e.length-1;i++){
                System.out.println(e[i][i]);
            }*/
                                          System.out.println(Arrays.deepToString(e));
    }
}
```
