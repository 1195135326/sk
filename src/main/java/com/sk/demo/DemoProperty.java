package com.sk.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoProperty {
    private static final Logger log = LoggerFactory.getLogger(DemoProperty.class);

    private final Myproperty myproperty;

    @Autowired
    public DemoProperty(Myproperty my)
    {
        this.myproperty=my;
    }

    @GetMapping("/1")
    public String test(){
        log.info("-----etsts-----");
        return myproperty.toString();
    }
}
