package com.ts.user.control;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;
import com.ts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/queryUser")
    public ResultInfo queryUser(){
        return  userService.searchAllUser("","",1,10);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResultInfo addUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }




}
