package com.ts.system.producManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.producManager.UI.ProductInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    /***
     * *  新增
     *
     * @param productInfo 产品
     * @param file  图片
     * @return
     */
    public ResultInfo add(ProductInfo productInfo, MultipartFile file);

    /***
     * 修改
     *
     * @param productInfo  产品
     * @param file 图片
     * @return
     */
    public ResultInfo edit(ProductInfo productInfo, MultipartFile file);

    /***
     * 删除
     *
     * @param iID 产品ID
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
     * @param iID 产品ID
     * @return
     */
    public ResultInfo get(int iID);

    /**
     * 下载
     *
     * @param iID
     * @return
     */
    public ResultInfo downResource(int iID);

}
