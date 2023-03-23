package com.example.server.mapper;

import com.example.server.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScheduleMapper {
    //根据开课id查询课程
    @Select("select * from schedule where id = #{schid}")
    public Schedule selectBySchid(int schid);

}
