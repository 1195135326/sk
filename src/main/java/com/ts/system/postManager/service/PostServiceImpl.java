package com.ts.system.postManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.postManager.UI.PostInfo;
import com.ts.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService

{
    @Autowired
    private UserDao dao;

    @Override
    public ResultInfo searchPostByCode(String sUserCode) {
        return null;
    }

    @Override
    public ResultInfo searchAllPost(String sWhere, String sOrder, int pageIndex, int paheSize) {
        ResultInfo rs = new ResultInfo();
        try {
            rs.setRows(dao.queryUser(sWhere, sOrder, pageIndex, paheSize));
            rs.setTotal(3);
        }
        catch (Exception e){
            rs.setsErrorMsg(e.getLocalizedMessage());
        }
        return rs;
    }

    @Override
    public void addEditDel(PostInfo postInfo) {

    }
}
