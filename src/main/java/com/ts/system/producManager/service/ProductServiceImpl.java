package com.ts.system.producManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.producManager.UI.ProductInfo;
import com.ts.system.producManager.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by slq on 2019/8/11.
 */
@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    /***
     * *  新增
     *
     * @param productInfo 产品
     * @param file  图片
     * @return
     */
    @Override
    public ResultInfo add(ProductInfo productInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                productInfo.setResourceName(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = productDao.add(productInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 修改
     *
     * @param productInfo  产品
     * @param file 图片
     * @return
     */
    @Override
    public ResultInfo edit(ProductInfo productInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                productInfo.setResourceName(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = productDao.edit(productInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 删除
     *
     * @param iID 产品ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = productDao.delete(iID);
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
            resultInfo = productDao.queryData();
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;    }

    /***
     * 查看
     *
     * @param iID 产品ID
     * @return
     */
    @Override
    public ResultInfo get(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = productDao.get(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 下载
     *
     * @param iID
     * @return
     */
    @Override
    public ResultInfo downResource(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = productDao.downResource(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
