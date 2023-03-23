package com.example.server.controller;

import com.example.server.entity.Teacher;
import com.example.server.mapper.TeacherMapper;
import com.example.server.utils.MD5Utils;
import com.example.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;
    @PostMapping("/teacher/register")
    public Result register(Teacher teacher){
        Teacher teacherExist = teacherMapper.selectTeacherById(teacher.getId());
        if(teacherExist != null) {
            return Result.failed("teacher账号已存在");
        }
        else{
            teacher.setPassword(MD5Utils.code(teacher.getPassword()));
            int a= teacherMapper.register(teacher);
            System.out.println(a);
            if(a==1){
                return Result.success("注册成功");
            }
            else{
                return Result.failed("注册失败");
            }
        }
    }
    //登录teacher
    @PostMapping("/teacher/login")
    public Result teacherLogin(Teacher teacher){
        Teacher teacherExist = teacherMapper.selectTeacherById(teacher.getId());
        if(teacherExist == null){
            return Result.failed("用户不存在，请注册");
        }
        else{
            if(MD5Utils.code(teacher.getPassword())!=teacherMapper.selectTeacherPasswordById(teacher.getId())){
                return Result.failed("用户名或密码错误");
            }
            else{
                return Result.success("登陆成功",teacherMapper.login(teacher.getId(),MD5Utils.code(teacher.getPassword())));
            }
        }
    }

    //根据id查询teacher
    @GetMapping("/teacher/{id}")
    public Teacher SelectTeacherById(@PathVariable String id){
        return teacherMapper.selectTeacherById(id);
    }
    //查询全部teacher
    @GetMapping("/teacher")
    public List<Teacher> getStudent(){
        return teacherMapper.getTeacher();
    }

    //更新teacher
    @PutMapping("/teacher")
    public String updateTeacher(){
        return "更新teacher";
    }

    //根据id删除teacher
    @DeleteMapping("/teacher/{id}")
    public String deleteTeacherById(@PathVariable String id){
        int a = teacherMapper.deleteTeacherById(id);
        if (a==1){
            return "删除teacher成功";
        }
        else {
            return "删除失败";
        }
    }
}
