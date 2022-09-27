package com.ransibi.springbootmybatis.domain;

import lombok.Data;

/**
 * @ClassName &{NAME}
 * @Description Book
 * @Author code-ran
 * @Date 2022/7/18 03:21
 * @Version1.0
 */
@Data
public class Book {
    private Integer id;
    private String bookNumber;//书籍编号
    private String bookName;//书籍名称
    private String bookType;//所属类别
    private Float bookPrize;//价格
    private String author;//作者
    private String bookPublisher;//出版社
}
