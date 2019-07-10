package com.ts.app;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
@ComponentScan("com.ts")
public class TaxSoftApplication {
    @Autowired
    private NamedParameterJdbcTemplate con;

    public static void main(String[] args) {
        SpringApplication.run(TaxSoftApplication.class, args);
    }

    @RequestMapping(value = "/demo1")
    public String demo1(Map map)
    {
        String sCode = "";

        System.out.println("sss");
        return "sk";
    }

}
