package com.example.server.controller;

import com.example.server.entity.*;
import com.example.server.mapper.CourseMapper;
import com.example.server.mapper.TeacherServiceMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        SelectCourse update_grade = teacherServiceMapper.getDataBySchidAndSid(selectCourse.getSid(),selectCourse.getSchid());
        if (selectCourse.getGrade1()!=null){
            update_grade.setGrade1(selectCourse.getGrade1());
        }
        if (selectCourse.getGrade2()!=null){
            update_grade.setGrade2(selectCourse.getGrade2());
        }
        if (selectCourse.getGrade3()!=null){
            update_grade.setGrade3(selectCourse.getGrade3());
        }
        if (selectCourse.getGrade4()!=null){
            update_grade.setGrade4(selectCourse.getGrade4());
        }
        if (selectCourse.getGrade5()!=null){
            update_grade.setGrade5(selectCourse.getGrade5());
        }
        int a = teacherServiceMapper.updateStudentGrade(update_grade);
        if(a==1){
            return "更新成功";
        }
        else{
            return"更新失败";
        }
    }

    //取消已经开课课程
    @DeleteMapping("/teacherService/deleteTeacherCourse")
    public String deleteTeacherCourse(String tid,String cid){
        int a = teacherServiceMapper.deleteTeacherCourse(tid,cid);
        if (a==1){
            return "删除成功";
        }
        else{
            return "删除失败";
        }
    }
}
