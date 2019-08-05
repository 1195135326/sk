package com.ts.user.service;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;
import com.ts.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService

{
    @Autowired
    private UserDao dao;

    @Override
    public ResultInfo searchUserByCode(String sUserCode) {
        return null;
    }

    @Override
    public ResultInfo searchAllUser(String sWhere, String sOrder, int pageIndex, int paheSize) {
        return null;
    }

    @Override
    public void addEditDel(UserInfo userInfo) {

    }
}
