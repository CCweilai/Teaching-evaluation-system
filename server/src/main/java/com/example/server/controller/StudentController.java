package com.example.server.controller;

import com.example.server.entity.Student;
import com.example.server.mapper.StudentMapper;
import com.example.server.utils.MD5Utils;
import com.example.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    @PostMapping("/student/register")
    public Result register(Student student){
        Student studentExist = studentMapper.selectStudentById(student.getId());
        if(studentExist != null) {
            return Result.failed("admin账号已存在");
        }
        else{
            student.setPassword(MD5Utils.code(student.getPassword()));
            int a= studentMapper.register(student);
            System.out.println(a);
            if(a==1){
                return Result.success("注册成功");
            }
            else{
                return Result.failed("注册失败");
            }
        }
    }
    @PostMapping("/student/login")
    //登录student
    public Result studentLogin(Student student){
        Student studentExist = studentMapper.selectStudentById(student.getId());
        if(studentExist == null){
            return Result.failed("用户不存在，请注册");
        }
        else{
            if(MD5Utils.code(student.getPassword())!=studentMapper.selectStudentPasswordById(student.getId())){
                return Result.failed("用户名或密码错误");
            }
            else{
                return Result.success("登陆成功",studentMapper.login(student.getId(),MD5Utils.code(student.getPassword())));
            }
        }
    }
    //添加student
    @PostMapping("/student")
    public String addStudent(){
        return "添加admin";
    }

    //根据id查询student
    @GetMapping("/student/{id}")
    public Student SelectStudentById(@PathVariable String id){
        return studentMapper.selectStudentById(id);
    }
    //查询全部student
    @GetMapping("/student")
    public List<Student> getStudent(){
        return studentMapper.getStudent();
    }

    //更新student
    @PutMapping("/student")
    public String updateStudent(){
        return "更新admin";
    }

    //根据id删除student
    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable String id){
        int a = studentMapper.deleteStudentById(id);
        if (a==1){
            return "删除student成功";
        }
        else {
            return "删除失败";
        }
    }
}
