package com.ts.system.newsManager.control;

import com.ts.system.newsManager.dao.NewsDao;
import com.ts.system.newsManager.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class NewsManagerController
{
    @Autowired
    private NewsService s;



}
