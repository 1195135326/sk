package com.ts.system.CaseManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.CaseManager.UI.CaseInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by slq on 2019/8/11.
 */
public interface CaseService {
    /***
    * 新增
     *
     * @param caseInfo 案例
     * @param file  图片
     * @return
             */
    public ResultInfo add(CaseInfo caseInfo, MultipartFile file);

    /***
     * 修改
     *
     * @param caseInfo  案例
     * @param file 图片
     * @return
     */
    public ResultInfo edit(CaseInfo caseInfo,  MultipartFile file);

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
