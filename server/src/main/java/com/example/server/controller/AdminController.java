package com.example.server.controller;

import com.example.server.entity.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    //注册admin
    @GetMapping("/admin/register")
    public String adminRegister(Admin admin){
        return "nihao";
    }


    //查询全部
    @GetMapping("/admin")
    public List getAdmin(){
        return adminMapper.getAdmin();
    }

    //根据id查询admin
    @GetMapping("/admin/{id}")
    public List getAdminById(@PathVariable int id){
        return adminMapper.selectById(id);
    }

    //添加admin
    @PostMapping("/admin")
    public String addAdmin(){

        return "添加admin成功";
    }

    //更新admin
    @PutMapping("/admin")
    public String updateAdmin(){
        return "更新admin成功";
    }

    //根据id删除admin
    @DeleteMapping("/admin/{id}")
    public String deleteById(@PathVariable int id){
        return "根据id删除admin";
    }

}
