package com.example.server.mapper;

import com.example.server.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseMapper {
    //根据课程id查询
    @Select("select * from course where id=#{cid}")
    public Course getCourseById(String cid);
}
