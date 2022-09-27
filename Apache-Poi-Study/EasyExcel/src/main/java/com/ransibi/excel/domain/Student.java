package com.ransibi.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName &{NAME}
 * @Description Student
 * @Author code-ran
 * @Date 2022/5/4 15:16
 * @Version1.0
 */
@Data
//@ContentRowHeight  //内容的行高
//@HeadRowHeight    //表头的行高
public class Student {

    /**
     * id
     */
    @ExcelProperty(value = "ID",index = 0)
    private String id;

    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名",index = 1)
    @ColumnWidth(20)
    private String name;


    /**
     * 学生性别
     */
    @ExcelProperty(value = "学生性别",index = 2)
    private String gender;

    /**
     * 学生出生日期
     */
    @ExcelProperty(value = "学生出生日期",index = 3)
    @ColumnWidth(20)//控制列宽
    private Date birthday;


}
