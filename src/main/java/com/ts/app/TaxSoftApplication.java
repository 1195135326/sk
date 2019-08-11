package com.ts.app;

import com.ts.entity.User;
import com.ts.jdbc.SysDB;
import com.ts.system.newsManager.UI.Exceptions;
import com.ts.user.UI.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
@ComponentScan("com.ts")
@Controller
public class TaxSoftApplication {
    @Autowired
    private NamedParameterJdbcTemplate con;

    public static void main(String[] args) {
        SpringApplication.run(TaxSoftApplication.class, args);
    }

    @RequestMapping(value = "/demo1")
    @Transactional
    public String demo1(Map map)
    {
        String sCode = "";
        sCode = SysDB.getStringValue(con,"select fname from s_test where id=1");
//        SysDB.execute(con,"insert into s_test values (3,'slq1','时留旗1')");
//        sCode = SysDB.getStringValue(con,"select fname from s_test where id=2 ssasfa");
        System.out.println(sCode);
        map.put("name",sCode);
        return "sk";
    }

    @ResponseBody
    @PostMapping(value = "/test")
    public UserInfo test(@RequestBody UserInfo userInfo)
    {
        try {

            userInfo.setUserName("沈科test");
        }
        catch (Exception e)
        {

        }
        return userInfo;
    }

    @RequestMapping(value = "/console")
    public String onTest()
    {
        return "/control";
    }

    @RequestMapping(value = "/index")
    public String onIndex()
    {
        return "/index";
    }
}
