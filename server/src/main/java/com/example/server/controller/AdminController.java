package com.example.server.controller;

import com.example.server.entity.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.utils.MD5Utils;
import com.example.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    //注册admin
    @PostMapping("/admin/register")
    public Result adminRegister(Admin admin){
        System.out.println(admin);
        Admin adminExist = adminMapper.selectByName(admin.getName());
        if(adminExist != null) {
            return Result.failed("admin账号已存在");
        }
        else{
            admin.setPassword(MD5Utils.code(admin.getPassword()));
            int a= adminMapper.register(admin);
            System.out.println(a);
            if(a==1){
                return Result.success("注册成功");
            }
            else{
                return Result.failed("注册失败");
            }
        }
    }
    @PostMapping("/admin/login")
    //登录admin
    public Result adminLogin(Admin admin){
        Admin adminExist = adminMapper.selectByName(admin.getName());
        if(adminExist == null){
            return Result.failed("用户名不存在，请注册");
        }
        else{
            if(MD5Utils.code(admin.getPassword())!=adminMapper.selectPasswordByName(admin.getName())){
                return Result.failed("用户名或密码错误");
            }
            else{
                return Result.success("登陆成功",adminMapper.login(admin.getName(),MD5Utils.code(admin.getPassword())));
            }
        }
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
