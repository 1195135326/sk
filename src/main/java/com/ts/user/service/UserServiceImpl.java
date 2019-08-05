package com.ts.user.service;

import com.ts.entity.ResultInfo;
import com.ts.jdbc.SysDB;
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
        ResultInfo rs = new ResultInfo();
        try {
            rs.setRows(dao.queryUser(sWhere, sOrder, pageIndex, paheSize));
        }
        catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public void addEditDel(UserInfo userInfo) {

    }
}
