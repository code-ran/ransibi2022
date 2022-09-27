package com.ransibi.streampractice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName &{NAME}
 * @Description Author
 * @Author code-ran
 * @Date 2022/5/16 17:52
 * @Version1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//用于去重使用
public class Author implements Comparable<Author> {
    private Long id;//id
    private String name;//姓名
    private Integer age;//年龄
    private String intro;//简介
    private List<Book> books;//作品

    //实现流中的soretd方法
    @Override
    public int compareTo(Author o) {
        //当前的年龄与传入年龄进行比较
        return o.getAge() - this.getAge();
    }
}
