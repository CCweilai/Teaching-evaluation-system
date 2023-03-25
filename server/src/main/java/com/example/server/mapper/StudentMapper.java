package com.example.server.mapper;

import com.example.server.entity.Admin;
import com.example.server.entity.Student;
import org.apache.ibatis.annotations.*;

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
    public String selectStudentPasswordById(String id);
    //根据name查询信息

    @Select("select * from student where id = #{id} and password = #{password}")
    public Student login(String id,String password);

    //注册 增加
    @Insert("insert into student values (#{id},#{name},#{password}),#{sex},#{age},#{telephone},#{email}")
    int register(Student student);
    //根据id删除
    @Delete("delete from student where id = #{id}")
    int deleteStudentById(String id);
    @Update("update student set name=#{name},password= #{password},sex=#{sex},age=#{age},telephone= #{telephone},email=#{email} where id =#{id}")
    int updateStudent(Student student);

}
