package com.ts.system.producManager.dao;


import com.ts.entity.ResultInfo;
import com.ts.system.producManager.UI.ProductInfo;

public interface ProductDao {

         /***
          * *  新增
         *
         * @param productInfo 产品
         * @param b  图片
         * @return
         */
        public ResultInfo add(ProductInfo productInfo, byte [] b);

        /***
         * 修改
         *
         * @param productInfo  产品
         * @param b 图片
         * @return
         */
        public ResultInfo edit(ProductInfo productInfo, byte [] b);

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