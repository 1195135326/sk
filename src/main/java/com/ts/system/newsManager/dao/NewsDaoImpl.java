package com.ts.system.newsManager.dao;

import com.ts.system.newsManager.UI.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private NamedParameterJdbcTemplate con;

    @Override
    public void add(NewsInfo newsInfo) {
        String sql = "insert into s_neds";


    }

    @Override
    public NewsInfo show(int id) {
        return null;
    }


}
