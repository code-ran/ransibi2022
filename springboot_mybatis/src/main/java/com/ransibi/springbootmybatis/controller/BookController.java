package com.ransibi.springbootmybatis.controller;

import com.ransibi.springbootmybatis.dao.BookMapper;
import com.ransibi.springbootmybatis.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/info")
    public List<Book> getStudents() {
        List<Book> lst = bookMapper.selectAll();
        return lst;
    }
//    @PostMapping("/add")
//    public void addStudent(@RequestBody Student student){
//        StudentMapper.insert(student);
//    }
//    @PostMapping("delete")
//    public void deleteStudent(@RequestBody Student student){
//        StudentMapper.deleteById(student);
//    }
//    @PostMapping("update")
//    public void updateStudent(@RequestBody Student student){
//        StudentMapper.updateById(student);
//    }
}
