package com.example.server.mapper;

import com.example.server.entity.Admin;
import com.example.server.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    //查询全部
    @Select("select * from student")
    public List<Student> getStudent();
    //根据id查询全部信息
    @Select("select * from student where id=#{id}")
    public Student selectStudentById(String id);
    //根据student的id查询password
    @Select("select password from student where id = #{id}")
    public Student selectStudentPasswordById(String id);
    //根据name查询信息


    //注册 增加
    @Insert("insert into student values (#{id},#{name},#{password}),#{sex},#{age},#{telephone},#{email}")
    int register(Student student);
    //根据id删除
    @Delete("delete from student where id = #{id}")
    int deleteStudentById(String id);

}
