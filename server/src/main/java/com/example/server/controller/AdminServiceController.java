package com.example.server.controller;

import com.example.server.entity.Course;
import com.example.server.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminServiceController {
    @Autowired
    CourseMapper courseMapper;

    //增加课程
    @PostMapping("/adminService/addCourse")
    public String addCourse(Course course){
        int a = courseMapper.addCourse(course);
        if (a==1){
            return "添加成功";
        }
        else{
            return "添加失败";
        }
    }
    //删除课程
    @DeleteMapping("/adminService/deleteCourse")
    public String deleteCourse(String id){
        int a = courseMapper.deleteCourse(id);
        if (a==1){
            return "删除成功";
        }
        else{
            return "删除失败";
        }
    }
    //更改课程
    @PutMapping("/adminService/updateCourse")
    public String updateCourse(Course course){
        Course update_course = courseMapper.getCourseById(course.getId());
        if (course.getName()!=null){
            update_course.setName(course.getName());
        }
        if (course.getInfo()!=null){
            update_course.setInfo(course.getInfo());
        }
        if (course.getNature()!=null){
            update_course.setNature(course.getNature());
        }
        int a = courseMapper.updateCourse(update_course);
        if (a==1){
            return "更改成功";
        }
        else{
            return "更改失败";
        }
    }
    //查询课程
    @GetMapping("/adminService/getAllCourse")
    public List<Course> getAllCourse(){
        return courseMapper.getAllCourse();
    }


}
