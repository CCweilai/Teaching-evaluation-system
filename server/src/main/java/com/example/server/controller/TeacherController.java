package com.example.server.controller;

import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
import com.example.server.mapper.StudentMapper;
import com.example.server.mapper.TeacherMapper;
import com.example.server.utils.MD5Utils;
import com.example.server.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TeacherController {
    TeacherMapper teacherMapper;
    @PostMapping("/teacher/register")
    public Result register(Teacher teacher){
        Teacher teacherExist = teacherMapper.selectTeacherPasswordById(teacher.getId());
        if(teacherExist != null) {
            return Result.failed("admin账号已存在");
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
    //添加student

    //根据id查询student
    @GetMapping("/teacher/{id}")
    public Teacher SelectTeacherById(@PathVariable String id){
        return teacherMapper.selectTeacherById(id);
    }
    //查询全部student
    @GetMapping("/teacher")
    public List<Teacher> getStudent(){
        return teacherMapper.getTeacher();
    }

    //更新student
    @PutMapping("/teacher")
    public String updateTeacher(){
        return "更新teacher";
    }

    //根据id删除student
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
