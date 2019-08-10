package com.ts.user.service;

import com.ts.entity.ResultInfo;
import com.ts.user.UI.UserInfo;

public interface UserService
{
    public ResultInfo searchUserByCode(String sUserCode);

    public ResultInfo searchAllUser(String sWhere, String sOrder, int pageIndex, int paheSize);

    public ResultInfo addUser(UserInfo userInfo);

    public ResultInfo editUser(UserInfo userInfo);

    public ResultInfo delUser(UserInfo userInfo);
}
