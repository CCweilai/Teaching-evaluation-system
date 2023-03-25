package com.example.server.controller;

import com.example.server.entity.Schedule;
import com.example.server.entity.SelectCourse;
import com.example.server.mapper.StudentServiceMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentServiceController {
    @Autowired
    StudentServiceMapper studentServiceMapper;

    //选课
    @PostMapping("/studentService/selectCourse")
    public String pickCourse(String sid,int schid){
        int room_now = studentServiceMapper.getCountBySchid(schid);
        int room =studentServiceMapper.getRoomBySchid(schid);
        SelectCourse selectCourse= new SelectCourse();
        selectCourse.setSid(sid);
        selectCourse.setSchid(schid);
        selectCourse.setGrade1(0);
        selectCourse.setGrade2(0);
        selectCourse.setGrade3(0);
        selectCourse.setGrade4(0);
        selectCourse.setGrade5(0);
        if ( room_now>=room ){
            return"课容量已满,无法选课";
        }
        else{
            System.out.println(room_now);
            System.out.println(room);

            int a= studentServiceMapper.pickCourse(selectCourse) ;
            System.out.println(a);
            if(a==1){
                return "选课成功";
            }
            else{
                return "选课失败";
            }
        }
    }

    //查看自己的成绩
    @PostMapping("/studentService/selectGrade")
    public SelectCourse selectGrade(String sid,int schid){
        return studentServiceMapper.selectGrade(sid,schid);
    }

    //查看自己所有的选课
    @PostMapping("/studentService/selectAllCourse")
    public List<Schedule> selectAllCourse(String sid){
        return studentServiceMapper.selectCourse(sid);
    }

    @Delete("/studentService/deleteStudentCourse")
    public String deleteStudentCourse(Integer schid,String sid){
        int a= studentServiceMapper.deleteStudentCourse(schid,sid);
        if(a==1){
            return "退课成功";
        }
        else{
            return "退课失败";
        }
    }
}
