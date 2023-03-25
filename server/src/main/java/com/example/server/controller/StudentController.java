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
    //注册学生账号
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
        //无实际需求，不予实现
        return "添加admin,无实际需求，未实现";
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
    public String updateStudent(Student student){
        Student update_Student = studentMapper.selectStudentById(student.getId());
        if (student.getName()!=null){
            update_Student.setName(student.getName());
        }
        if (student.getAge() != null){
            update_Student.setAge(student.getAge());
        }
        if (student.getSex() !=  null ){
            update_Student.setSex(student.getSex());
        }
        if (student.getTelephone()!=null){
            update_Student.setTelephone(student.getTelephone());
        }
        if (student.getEmail()!=null){
            update_Student.setEmail(student.getEmail());
        }
        if (student.getPassword()!=null){
            update_Student.setPassword(MD5Utils.code(student.getPassword()));
        }
        int a = studentMapper.updateStudent(update_Student);
        if(a==1){
            return "更新成功";
        }
        else{
            return"更新失败";
        }
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
