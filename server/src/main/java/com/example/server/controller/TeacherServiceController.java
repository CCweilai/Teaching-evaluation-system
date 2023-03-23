package com.example.server.controller;

import com.example.server.entity.Course;
import com.example.server.entity.Schedule;
import com.example.server.entity.SelectCourse;
import com.example.server.entity.Student;
import com.example.server.mapper.CourseMapper;
import com.example.server.mapper.TeacherMapper;
import com.example.server.mapper.TeacherServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherServiceController {
    @Autowired
    TeacherServiceMapper teacherServiceMapper;
    CourseMapper courseMapper;
    //开课 新增课程
    @PostMapping("/teacherService/createCourse")
    public String createCourse(Schedule schedule){
        //查询无课，向管理员申请
        Course courseExit = courseMapper.getCourseById(schedule.getCid());
        if(courseExit == null){
            return "课程不存在，请向管理员申请开课";
        }
        //有课则更改 无课撤退
        int a = teacherServiceMapper.createCourse(schedule);
        if(a==1){
            return "开课成功";
        }
        else{
            return "开课失败";
        }
    }

    //查询已经开设课程
    @PostMapping("/teacherService/teacherGetCourse")
    public List<Schedule> teacherGetCourse(String tid){
        return teacherServiceMapper.teacherGetCourse(tid);
    }



    //查询已开课程全部学生
    @GetMapping("/teacherService/getStudentInCourse/{schid}")
    public List<Student> getStudentInCourse(@PathVariable Integer schid){
        return teacherServiceMapper.getStudentInCourse(schid);
    }


    //查询成绩
    @GetMapping("/teacherService/getStudentCourse")
    public List<SelectCourse> getStudentCourse(Integer schid,String sid){
        return teacherServiceMapper.getStudentGrade(schid,sid);

    }
    //更改成绩
    @PostMapping("/teacherService/updateStudentGrade")
    public String updateStudentGrade(SelectCourse selectCourse){
        int a=teacherServiceMapper.updateStudentGrade(selectCourse);
        if(a==1){
            return "修改成功";
        }
        else{
            return "修改失败";
        }

    }

}
