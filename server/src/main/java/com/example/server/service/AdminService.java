package com.example.server.service;


import com.example.server.entity.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    /**
     * admin 注册
     * @param admin
     * @return Result
     */
    public Result register(Admin admin){
        Result result = new Result();

        result.setData(null);
        try{
            Admin adminExist = adminMapper.selectByName(admin.getName());
            if(adminExist != null){
                result.setMessage("admin账号已存在");
            }
            else{
                adminMapper.register(admin);
                result.setMessage("注册成功");
                result.setData(admin);
            }
        } catch (Exception e) {
                result.setMessage(e.getMessage());
                e.printStackTrace();
            }
            return result;
    }
   public Result login(Admin admin) {
       return null;

   }

}
