package com.example.server.controller;


import org.springframework.web.bind.annotation.*;

@RestController

public class StudentController {


    //根据id查询admin
    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable int id){
        return "admin hello";
    }

    //添加admin
    @PostMapping("/student")
    public String addStudent(){
        return "添加admin";
    }

    //更新admin
    @PutMapping("/student")
    public String updateStudent(){
        return "更新admin";
    }

    //根据id删除admin
    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable int id){
        return "根据id删除admin";
    }


}
