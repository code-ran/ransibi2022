package com.ransibi.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ransibi.excel.domain.Student;

/**
 * @ClassName &{NAME}
 * @Description StudentListener
 * @Author code-ran
 * @Date 2022/5/4 15:51
 * @Version1.0
 */
public class StudentListener extends AnalysisEventListener<Student> {
    @Override
    public void invoke(Student data, AnalysisContext context) {
        System.out.println("student = "+ data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
