package com.ts.user.dao;

import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.jdbc.SysDB;
import com.ts.user.UI.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try {
            sql = "insert into taxsoft.s_user (fid,fcode,fname,floginaccount,fpwd,fdeptname,fphonenum,ftelnum,femail,ftime) " +
                    "values (" + iMaxID + ",'" + userInfo.getUserCode() + "','" + userInfo.getUserName() + "','" + userInfo.getLoginAccount() +
                    "','" + userInfo.getLoginPassword() + "','" + userInfo.getDeptName() + "','" + userInfo.getPhoneNum() +
                    "','" + userInfo.getTelNum() + "','" + userInfo.getEmail() + "',to_timestamp(" + new Date().getTime() + "))";
            SysDB.execute(con, sql);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void edit(UserInfo userInfo) throws Exception {
        String sql = "";
        try {
            sql = "update taxsoft.s_user set fname='" + userInfo.getUserName() + "',floginaccount='"+userInfo.getLoginAccount()+
                    "',fpwd='"+userInfo.getLoginPassword()+"',fdeptname='"+userInfo.getDeptName()+"',fphonenum='"+userInfo.getPhoneNum()+
                    "',ftelnum='"+userInfo.getTelNum()+"',femail='"+ userInfo.getEmail()+"',ftime=to_timestamp("+new Date().getTime()+")" +
                    " where fcode='"+userInfo.getUserCode()+"'";
            SysDB.execute(con, sql);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(String sUserCode) throws Exception {
        SysDB.execute(con,"delete from taxsoft.s_user where fcode='"+sUserCode+"'");
    }

    @Override
    public UserInfo getUser(String sUserCode) throws Exception {
        String sql = "select * from taxsoft.s_user where fcode='"+sUserCode+"'";
        UserInfo userInfo = new UserInfo();
        Map<String,Object> mp;
        try {
            mp = SysDB.getMapValue(con,sql,new HashMap<>());
            userInfo.setUserCode(SysString.getMapStr(mp,"fcode"));
            userInfo.setUserName(SysString.getMapStr(mp,"fname"));
            userInfo.setLoginAccount(SysString.getMapStr(mp,"floginaccount"));
            userInfo.setLoginPassword(SysString.getMapStr(mp,"fpwd"));
            userInfo.setDeptName(SysString.getMapStr(mp,"fdeptname"));
            userInfo.setPhoneNum(SysString.getMapStr(mp,"fphonenum"));
            userInfo.setTelNum(SysString.getMapStr(mp,"ftelnum"));
            userInfo.setEmail(SysString.getMapStr(mp,"femail"));

        }catch (Exception e){
            throw e;
        }
        return userInfo;
    }

    @Override
    public List<Map<String,Object>> queryUser(String sWhere, String sOrder, int pageIndex, int pageSize) throws Exception {
        int ibeg = pageSize * (pageIndex-1);
//        return con.queryForList("select a.* from taxsoft.s_user a  order by 1 limit "+pageSize+" offset "+ibeg+" ",new HashMap<>());
        return con.queryForList("select a.* from taxsoft.s_user a  order by 1 ",new HashMap<>());
    }

    @Override
    public boolean isExists(UserInfo userInfo) throws Exception {
        return SysDB.getIntValue(con, "select count(1) from taxsoft.s_user where fcode='"+userInfo.getUserCode()+"'")==0?false:true;
    }

    @Override
    public int getCount() throws Exception {
        return SysDB.getIntValue(con, "select count(1) from taxsoft.s_user");
    }
}
