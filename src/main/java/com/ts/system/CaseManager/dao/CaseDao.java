package com.ts.system.CaseManager.dao;

import com.ts.entity.ResultInfo;
import com.ts.system.CaseManager.UI.CaseInfo;
import com.ts.system.newsManager.UI.NewsInfo;

/**
 * Created by slq on 2019/8/11.
 */
public interface CaseDao {
    /***
     * 新增
     *
     * @param caseInfo 案例
     * @param b  图片
     * @return
     */
    public ResultInfo add(CaseInfo caseInfo, byte [] b);

    /***
     * 修改
     *
     * @param caseInfo  案例
     * @param b 图片
     * @return
     */
    public ResultInfo edit(CaseInfo caseInfo, byte [] b);

    /***
     * 删除
     *
     * @param iID 案例ID
     * @return
     */
    public ResultInfo delete(int iID);

    /***
     * 查询
     *
     * @return
     */
    public ResultInfo queryData();


    /***
     * 查看
     *
     * @param iID 案例ID
     * @return
     */
    public ResultInfo get(int iID);



}
