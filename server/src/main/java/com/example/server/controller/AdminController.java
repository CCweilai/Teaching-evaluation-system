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

    //登录admin
    @PostMapping("/admin/login")
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
    public Admin getAdminById(@PathVariable int id){
        return adminMapper.selectById(id);
    }

    //添加admin 注册逻辑
    @PostMapping("/admin")
    public String addAdmin(Admin admin){
        Admin adminExist = adminMapper.selectByName(admin.getName());
        if(adminExist!=null){
            return "账号存在，无法添加";
        }
        else{
            admin.setPassword(MD5Utils.code(admin.getPassword()));
            int a = adminMapper.register(admin);
            if(a==1){
                return "添加成功";
            }
            else{
                return "添加失败";
            }
        }
    }

    //更新admin
    @PutMapping("/admin")
    public String updateAdmin(Admin admin) {
        Admin adminExit = adminMapper.selectById(admin.getId());
        if (adminExit == null){
            return "更改错误，不存原账户";
        }
        // 更新实体对象的属性值，同时保留原始值
        Admin update_admin = adminMapper.selectById(admin.getId());
        if (admin.getName() != null) {
            update_admin.setName(admin.getName());
        }
        if (admin.getPassword() != null) {
            update_admin.setPassword(MD5Utils.code(admin.getPassword()));
        }

        int a = adminMapper.updateAdmin(update_admin);
        if(a==1){
            return "更新admin成功";
        }
        else{
            return "更新admin失败";
        }
    }

    //根据id删除admin
    @DeleteMapping("/admin/{id}")
    public String deleteById(@PathVariable int id){
        int a = adminMapper.deleteAdminById(id);
        if (a==1){
            return "根据id删除admin成功";
        }
        else{
            return "根据id删除admin失败";
        }
    }
}
