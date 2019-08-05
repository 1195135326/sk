package com.ts.user.dao;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;

import java.util.ArrayList;

public interface UserDao {
    public void add(UserInfo userInfo) throws Exception;

    public void edit(String sUserCode) throws Exception;

    public void delete(String sUserCode) throws Exception;

    public UserInfo getUser(String sUserCode) throws Exception;

    public ArrayList<UserInfo> queryUser(String sWhere, String sOrder, int pageIndex, int paheSize) throws Exception;
}
