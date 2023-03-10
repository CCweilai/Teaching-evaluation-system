package com.example.server.mapper;

import com.example.server.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Insert("insert into admin (name,password) values (#{name},#{password})")
    void register(Admin admin);
    //查询所有admin
    @Select("select * from admin")
    public List<Admin> getAdmin();

    //根据id查询admin
    @Select("select * from admin where id = #{id}")
    public List<Admin> selectById(int id );

    //根据name查询
    @Select("select * from admin where name = #{name}")
    public Admin selectByName(String name);



}
