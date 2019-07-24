package com.ts.system.newsManager.service;

import com.ts.system.newsManager.UI.NewsInfo;
import com.ts.system.newsManager.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsServiceImpl implements NewsService
{
    @Autowired
    private NewsDao dao;

    @Override
    public NewsInfo show(int id,int type) {

        dao.show(id);
        return null;
    }
}
