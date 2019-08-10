package com.ts.system.newsManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.newsManager.UI.NewsInfo;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService
{
    /***
     * 新增
     *
     * @param newsInfo 新闻
     * @param file  图片
     * @return
     */
    public ResultInfo add(NewsInfo newsInfo, MultipartFile file);

    /***
     * 修改
     *
     * @param newsInfo  新闻
     * @param file 图片
     * @return
     */
    public ResultInfo edit(NewsInfo newsInfo,  MultipartFile file);

    /***
     * 删除
     *
     * @param iID 新闻ID
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
     * @param iID 新闻ID
     * @return
     */
    public ResultInfo get(int iID);


}
