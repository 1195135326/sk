package com.ts.user.dao;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate con;


    @Override
    public void add(UserInfo userInfo) throws Exception {

    }

    @Override
    public void edit(String sUserCode) throws Exception {

    }

    @Override
    public void delete(String sUserCode) throws Exception {

    }

    @Override
    public UserInfo getUser(String sUserCode) throws Exception {
        return null;
    }

    @Override
    public ArrayList<UserInfo> queryUser(String sWhere, String sOrder, int pageIndex, int paheSize) throws Exception {
        return null;
    }
}
