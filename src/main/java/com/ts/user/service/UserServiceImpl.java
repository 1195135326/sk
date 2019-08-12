package com.ts.user.service;

import com.ts.entity.ResultInfo;
import com.ts.jdbc.SysDB;
import com.ts.user.UI.UserInfo;
import com.ts.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
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
            rs.setTotal(dao.getCount());
        }
        catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public ResultInfo addUser(UserInfo userInfo) {
        ResultInfo rs = new ResultInfo();
        try {
            int iMaxID = dao.getMaxID();
            if(dao.isExists(userInfo))
            {
                rs.setsErrorMsg("该客户已存在！");
                return rs;
            }
            dao.add(userInfo, iMaxID);
        }catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public ResultInfo editUser(UserInfo userInfo) {
        ResultInfo rs = new ResultInfo();
        try {
            dao.edit(userInfo);
        }catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public ResultInfo delUser(UserInfo userInfo) {
        ResultInfo rs = new ResultInfo();
        try {
            dao.delete(userInfo.getUserCode());
        }catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public ResultInfo getPassWord(String sUserCode) {
        ResultInfo rs = new ResultInfo();
        try {
            rs.setObj(dao.getUser(sUserCode));
        }catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

}
