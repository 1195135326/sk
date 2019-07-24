package com.ts.system.newsManager.dao;

import com.ts.system.newsManager.UI.NewsInfo;

public interface NewsDao {
    public void add(NewsInfo newsInfo);
    public NewsInfo show(int id);
}
