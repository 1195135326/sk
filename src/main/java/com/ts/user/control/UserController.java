package com.ts.user.control;

import com.ts.entity.ResultInfo;
import com.ts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        ResultInfo rs = new ResultInfo();
        rs =  userService.searchAllUser("","",1,10);
        return rs;
    }




}
