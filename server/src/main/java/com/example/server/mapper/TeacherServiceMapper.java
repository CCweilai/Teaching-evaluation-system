package com.example.server.mapper;

import com.example.server.entity.Schedule;
import com.example.server.entity.SelectCourse;
import com.example.server.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherServiceMapper {

    //开课 新增课
    @Insert("insert into schedule (id,tid,cid,place,time,info,room) values (#{id},#{tid},#{cid}),#{place},#{time},#{info},#{room}")
    public int createCourse(Schedule schedule);

    //查询自己已经开的课
    @Select("select * from schedule where tid = #{tid}")
    public List<Schedule> teacherGetCourse(String tid);

    // 查询自己开设课全部选课学生
    @Select("select id,name from student where id in (select sid from selectcourse where schid = #{schid})")
    public List<Student> getStudentInCourse(Integer schid);

    // 查看学生成绩
    @Select("select * from selectcourse where schid = #{schid},sid = #{sid}")
    public List<SelectCourse> getStudentGrade(Integer schid,String sid);

    //更改学生的成绩
    @Update("update selectcourse set grade1=#{grade1},grade2=#{grade2},grade3=#{grade3},grade4=#{grade4},grade5=#{grade5} where sid = #{sid} and schid=#{schid}")
    public int updateStudentGrade(SelectCourse selectCourse);

}
