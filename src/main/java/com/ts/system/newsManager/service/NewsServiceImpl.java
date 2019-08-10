package com.ts.system.newsManager.service;

import com.ts.entity.ResultInfo;
import com.ts.system.newsManager.UI.NewsInfo;
import com.ts.system.newsManager.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

@Component
public class NewsServiceImpl implements NewsService
{
    @Autowired
    private NewsDao newsDao;

    /***
     * 新增
     *
     * @param newsInfo 新闻
     * @param file  图片
     * @return
     */
    @Override
    public ResultInfo add(NewsInfo newsInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                newsInfo.setPicName(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = newsDao.add(newsInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 修改
     *
     * @param newsInfo  新闻
     * @param file 图片
     * @return
     */
    @Override
    public ResultInfo edit(NewsInfo newsInfo, MultipartFile file) {
        byte [] b = null;
        ResultInfo resultInfo =  new ResultInfo();
        try{
            if(file!=null)
            {
                newsInfo.setPicName(file.getOriginalFilename());
                b = file.getBytes();
            }
            resultInfo = newsDao.edit(newsInfo,b);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 删除
     *
     * @param iID 新闻ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = newsDao.delete(iID);
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
            resultInfo = newsDao.queryData();
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 查看
     *
     * @param iID 新闻ID
     * @return
     */
    @Override
    public ResultInfo get(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        try{
            resultInfo = newsDao.get(iID);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
