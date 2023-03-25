package com.example.server.mapper;

import com.example.server.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    //根据课程id查询
    @Select("select * from course where id=#{cid}")
    public Course getCourseById(String cid);

    //根据课程id查询
    @Select("select * from course where name=#{name}")
    public Course getCourseByName(String name);

    //查询全部
    @Select("select * from course")
    public List<Course> getAllCourse();

    //增加
    @Insert("insert into course values (#{id},#{name},#{info}),#{nature}")
    int addCourse(Course course);

    //删除
    @Delete("delete from course where id =#{id}")
    int deleteCourse(String id);

    //更改
    @Update("update course set name = #{name},info=#{info},nature = #{nature} where id = #{id}")
    int updateCourse(Course course);

}
