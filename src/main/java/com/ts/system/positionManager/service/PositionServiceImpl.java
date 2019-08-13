package com.ts.system.positionManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.positionManager.UI.PositionInfo;
import com.ts.system.positionManager.dao.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by slq on 2019/8/13.
 */
@Component
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    /***
     * *  新增
     *
     * @param positionInfo 招聘岗位
     * @return
     */
    @Override
    public ResultInfo add(PositionInfo positionInfo) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = positionDao.add(positionInfo);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }


    /***
     * 修改
     *
     * @param positionInfo  招聘岗位
     * @return
     */
    @Override
    public ResultInfo edit(PositionInfo positionInfo) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = positionDao.edit(positionInfo);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 删除
     *
     * @param iID 招聘岗位ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = positionDao.delete(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 查询
     *
     * @return
     */
    @Override
    public ResultInfo queryData() {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = positionDao.queryData();
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 查看
     *
     * @param iID 招聘岗位ID
     * @return
     */
    @Override
    public ResultInfo get(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = positionDao.get(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
