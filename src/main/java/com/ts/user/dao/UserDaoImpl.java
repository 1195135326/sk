package com.ts.user.dao;

import com.ts.entity.ResultInfo;
import com.ts.jdbc.SysDB;
import com.ts.user.UI.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate con;


    @Override
    public int getMaxID() throws Exception {
        int iMaxID;
        try {
            iMaxID = SysDB.getIntValue(con, "select max(fid) from taxsoft.s_user");
        }catch (Exception e){
            throw e;
        }
        return iMaxID;
    }

    @Override
    @Transactional
    public void add(UserInfo userInfo, int iMaxID) throws Exception {
        String sql = "";
        HashMap<String, Object> mp = new HashMap<>();
        try {
            sql = "insert into taxsoft.s_user (fid,fcode,fname,floginaccount,fpwd,faddress,fdeptname,fphonenum,ftelnum,femail,ftime) " +
                    "values (" + iMaxID + ",'" + userInfo.getUserCode() + "','" + userInfo.getUserName() + "','" + userInfo.getLoginAccount() +
                    "','" + userInfo.getLoginPassword() + "','" + userInfo.getAddress() + "','" + userInfo.getDeptName() + "','" + userInfo.getPhoneNum() +
                    "','" + userInfo.getTelNum() + "','" + userInfo.getEmail() + "',to_timestamp(" + new Date().getTime() + "))";
            SysDB.execute(con, sql);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void edit(String sUserCode) throws Exception {

    }

    @Override
    @Transactional
    public void delete(String sUserCode) throws Exception {

    }

    @Override
    public UserInfo getUser(String sUserCode) throws Exception {
        return null;
    }

    @Override
    public List<Map<String,Object>> queryUser(String sWhere, String sOrder, int pageIndex, int paheSize) throws Exception {
        return con.queryForList("select * from taxsoft.s_user",new HashMap<>());
    }
}
