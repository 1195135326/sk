package com.ts.system.postManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.postManager.UI.PostInfo;

public interface PostService
{
    public ResultInfo searchPostByCode(String sPostCode);

    public ResultInfo searchAllPost(String sWhere, String sOrder, int pageIndex, int paheSize);

    public void addEditDel(PostInfo postInfo);

}
