package com.ransibi.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.ransibi.excel.domain.Student;
import com.ransibi.excel.listener.StudentListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName &{NAME}
 * @Description ExcelTest
 * @Author code-ran
 * @Date 2022/5/4 15:30
 * @Version1.0
 */
public class ExcelTest {
    /**
     * 1.工作簿：一个Excel文件就是一个工作簿
     * 2.工作表：一个工作簿中可以有多个工作表（sheet页）
     */
    @Test
    public void test01() {
        /**
         * 需求：单实体导入
         * 导入Excel学员信息到系统。
         * 包含如下列：姓名、性别、出生日期
         * 模板详见：杭州黑马在线202003班学员信息.xls
         */
        //获得一个读的工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("学生基本信息.xlsx", Student.class, new StudentListener());
        //获得一个工作表对象
        ExcelReaderSheetBuilder sheetBuilder = readWorkBook.sheet();
        //读取工作表中的内容
        sheetBuilder.doRead();

    }

    @Test
    public void test02() {
        /**
         * 需求：单实体导出
         * 导出多个学生对象到Excel表格
         * 包含如下列：姓名、性别、出生日期
         * 模板详见：杭州黑马在线202003班学员信息.xlsx
         */
        //获取工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("学生基本信息表-导出.xlsx", Student.class);
        //获取工作表对象
        ExcelWriterSheetBuilder shett = writeWorkBook.sheet();

        //写
        shett.doWrite(initData());
    }

    private static List<Student> initData() {
        ArrayList<Student> students = new ArrayList<>();
        Student data = new Student();
        for (int i = 0; i < 10; i++) {
            data.setId("00"+i);
            data.setName("物联网0" + i);
            data.setBirthday(new Date());
            data.setGender("男");
            students.add(data);
        }
        return students;
    }
}
