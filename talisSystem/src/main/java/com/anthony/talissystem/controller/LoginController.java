package com.anthony.talissystem.controller;

import com.anthony.talissystem.pojo.Emp;
import com.anthony.talissystem.pojo.Result;
import com.anthony.talissystem.service.EmpService;
import com.anthony.talissystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController

public class LoginController {

    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result Mapping(@RequestBody Emp emp){
        log.info("Emp try to LOGIN :{}",emp);
        Emp e = empService.login(emp);

        //登录成功，生成令牌，下发令牌
        if(e != null){
            Map<String,Object> claism = new HashMap<>();
            claism.put("id",e.getId());
            claism.put("name",e.getName());
            claism.put("username",e.getUsername());
            //jwt生成
            String jwt = JwtUtils.generateJwt(claism);
            return Result.success(jwt);
        }

        //登录失败,返回错误信息
        return Result.error("用户名或密码错误");
    }

}
