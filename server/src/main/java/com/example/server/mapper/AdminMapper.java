package com.example.server.mapper;
import com.example.server.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    //查询所有admin
    @Select("select * from admin")
    public List<Admin> getAdmin();
    //根据id查询admin
    @Select("select * from admin where id = #{id}")
    public Admin selectById(int id);
    //根据name查询password
    @Select("select password from admin where name = #{name}")
    public String selectPasswordByName(String name);
    //根据name查询信息
    @Select("select * from admin where name = #{name}")
    public Admin selectByName(String name);
    //登录 根据name和password查询用户信息
    @Select("select * from admin where name = #{name} and password = #{password}")
    public Admin login(String name,String password);

    //注册 增加
    @Insert("insert into admin values (#{id},#{name},#{password})")
    int register(Admin admin);
    //根据id删除
    @Delete("delete from admin where id = #{id}")
    int deleteAdminById(int id);

    //修改 name,password
    @Update("update admin set name=#{name},password=#{password} where id = #{id} ")
    public int updateAdmin(Admin admin);


}


