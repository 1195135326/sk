package com.ts.casemanage.service;

import com.ts.entity.ResultInfo;
import com.ts.casemanage.UI.CaseInfo;
import com.ts.casemanage.dao.CaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by slq on 2019/8/11.
 */
@Component
public class CaseServieImpl implements CaseService {

    @Autowired
    private CaseDao caseDao;

    /***
     * 新增
     *
     * @param caseInfo 案例
     * @param file  图片
     * @return
     */
    @Override
    public ResultInfo add(CaseInfo caseInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                caseInfo.setPicname(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = caseDao.add(caseInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 修改
     *
     * @param caseInfo  案例
     * @param file 图片
     * @return
     */
    @Override
    public ResultInfo edit(CaseInfo caseInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                caseInfo.setPicname(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = caseDao.edit(caseInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 删除
     *
     * @param iID 案例ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = caseDao.delete(iID);
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
            resultInfo = caseDao.queryData();
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;    }

    /***
     * 查看
     *
     * @param iID 案例ID
     * @return
     */
    @Override
    public ResultInfo get(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = caseDao.get(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;    }
}
