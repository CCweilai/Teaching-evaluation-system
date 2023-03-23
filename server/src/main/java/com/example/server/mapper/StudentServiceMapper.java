package com.example.server.mapper;

import com.example.server.entity.Schedule;
import com.example.server.entity.SelectCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentServiceMapper {
    //通过schid查询课容量
    @Select("select room from schedule where id=#{schid}")
    public int getRoomBySchid(int schid);
    //根据选课id查询查询选课人数
    @Select("select count(schid=#{schid}) from selectcourse")
    public int getCountBySchid(int schid);
    //添加选课
    @Insert("insert into selectcourse(id,sid,schid) values (#{id},#{sid},#{schid})")
    int pickCourse(SelectCourse selectCourse);


    //查询全部成绩
    @Select("select * from selectcourse where sid=#{sid} and schid = #{schid}")
    public SelectCourse selectGrade(String sid,int schid);

   //根据学生id查询已选课
    @Select("select * from schedule where id in(select schid from selectcourse where sid =#{sid})")
    public List<Schedule> selectCourse(String sid);
}
