package com.ts.system.postManager.daoimpl;

import com.ts.system.postManager.UI.PostInfo;
import com.ts.system.postManager.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostDaoImpl implements PostDao {
    @Autowired
    private NamedParameterJdbcTemplate con;


    @Override
    public void add(PostInfo postInfo) throws Exception {

    }

    @Override
    public void edit(String sPostCode) throws Exception {

    }

    @Override
    public void delete(String sPostCode) throws Exception {

    }

    @Override
    public PostInfo getPost(String sPostCode) throws Exception {
        return null;
    }

    @Override
    public List<Map<String,Object>> queryPost(String sWhere, String sOrder, int pageIndex, int paheSize) throws Exception {
        return con.queryForList("select * from s_position",new HashMap<>());
    }
}
