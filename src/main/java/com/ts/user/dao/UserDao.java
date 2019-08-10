package com.ts.user.dao;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserDao {
    public int getMaxID() throws Exception;

    public void add(UserInfo userInfo,int iMaxID) throws Exception;

    public void edit(String sUserCode) throws Exception;

    public void delete(String sUserCode) throws Exception;

    public UserInfo getUser(String sUserCode) throws Exception;

    public List<Map<String,Object>> queryUser(String sWhere, String sOrder, int pageIndex, int paheSize) throws Exception;
}
