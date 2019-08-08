package com.ts.system.postManager.dao;

import com.ts.system.postManager.UI.PostInfo;
import com.ts.user.UI.UserInfo;

import java.util.List;
import java.util.Map;

public interface PostDao {
    public void add(PostInfo postInfo) throws Exception;

    public void edit(String sPostCode) throws Exception;

    public void delete(String sPostCode) throws Exception;

    public PostInfo getPost(String sPostCode) throws Exception;

    public List<Map<String,Object>> queryPost(String sWhere, String sOrder, int pageIndex, int pageSize) throws Exception;
}
