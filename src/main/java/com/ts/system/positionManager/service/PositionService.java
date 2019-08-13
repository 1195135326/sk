package com.ts.system.positionManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.positionManager.UI.PositionInfo;

/**
 * Created by slq on 2019/8/13.
 */
public interface PositionService {

    /***
     * *  新增
     *
     * @param positionInfo 招聘岗位
     * @return
     */
    public ResultInfo add(PositionInfo positionInfo);

    /***
     * 修改
     *
     * @param positionInfo  招聘岗位
     * @return
     */
    public ResultInfo edit(PositionInfo positionInfo);

    /***
     * 删除
     *
     * @param iID 招聘岗位ID
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
     * @param iID 招聘岗位ID
     * @return
     */
    public ResultInfo get(int iID);
}
