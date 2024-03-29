package com.ts.user.control;

import com.alibaba.fastjson.JSONObject;
import com.ts.comm.SysNumber;
import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;
import com.ts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/queryUser")
    public ResultInfo queryUser(){
        return  userService.searchAllUser("","", 1,10);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResultInfo addUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }

    @PostMapping("/delUser")
    @ResponseBody
    public ResultInfo delUser(@RequestBody UserInfo userInfo){
        return userService.delUser(userInfo);
    }

    @PostMapping("/editUser")
    @ResponseBody
    public ResultInfo editUser(@RequestBody UserInfo userInfo){
        return userService.editUser(userInfo);
    }

    @GetMapping("/randomCode")
    @ResponseBody
    public String getRandomCode(){
        return Math.random()+"";
    }

    @Value("${ts.admin.password}")
    private String pwd;

    @GetMapping("/login")
    public String login(@RequestParam("loginAccount") String username,
                        @RequestParam("loginPwd") String password,
                        Map<String, Object> map
                        ){
        if("Admin".equals(username) && pwd.equals(password)){
            return "control";
        }

        map.put("msg", "用户名密码错误");
        return "login";
    }

    @PostMapping("/loginUser")
    @ResponseBody
    public ResultInfo loginUser(@RequestParam("loginAccount") String usercode,
                                @RequestParam("loginPwd") String password){
        ResultInfo rs = userService.getPassWord(usercode);
        UserInfo userInfo = (UserInfo)rs.getObj();
        if(userInfo == null)
        {
            rs.setsErrorMsg("用户名密码错误");
        }
        else
        {
            if(!password.equals(userInfo.getLoginPassword()))
            {
                rs.setsErrorMsg("用户名密码错误");
            }
        }
        return rs;
    }


}
