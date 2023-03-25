package com.example.server.mapper;

import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    //查询全部
    @Select("select * from teacher")
    public List<Teacher> getTeacher();
    //根据id查询全部信息
    @Select("select * from teacher where id=#{id}")
    public Teacher selectTeacherById(String id);
    //根据student的id查询password
    @Select("select password from teacher where id = #{id}")
    public String selectTeacherPasswordById(String id);
    //根据name查询信息

    @Select("select * from teacher where id = #{id} and password = #{password}")
    public Teacher login(String id,String password);

    //注册 增加
    @Insert("insert into teacher values (#{id},#{name},#{password}),#{sex},#{age},#{telephone},#{email}")
    int register(Teacher teacher);
    //根据id删除
    @Delete("delete from teacher where id = #{id}")
    int deleteTeacherById(String id);

    //更改某条信息
    @Update("update teacher set name=#{name},password= #{password},sex=#{sex},age=#{age},telephone= #{telephone},email=#{email} where id =#{id}")
    int updateTeacher(Teacher teacher);
}
