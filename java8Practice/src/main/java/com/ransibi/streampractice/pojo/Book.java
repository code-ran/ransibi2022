package com.ransibi.streampractice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName &{NAME}
 * @Description Book
 * @Author code-ran
 * @Date 2022/5/16 17:52
 * @Version1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//用于去重使用
public class Book {
    private Long id;//id
    private String name;//书名
    private String category;//分类
    private Integer score;//评分
    private String intro;//简介
}
