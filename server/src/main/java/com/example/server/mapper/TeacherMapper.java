package com.example.server.mapper;

import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    public Teacher selectTeacherPasswordById(String id);
    //根据name查询信息


    //注册 增加
    @Insert("insert into teacher values (#{id},#{name},#{password}),#{sex},#{age},#{telephone},#{email}")
    int register(Teacher teacher);
    //根据id删除
    @Delete("delete from teacher where id = #{id}")
    int deleteTeacherById(String id);
}
