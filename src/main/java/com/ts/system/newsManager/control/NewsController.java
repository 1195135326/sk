package com.ts.system.newsManager.control;


import com.ts.comm.SysJson;
import com.ts.comm.SysNumber;
import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.system.newsManager.UI.NewsInfo;
import com.ts.system.newsManager.service.NewsService;
import com.ts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryData")
    public ResultInfo queryData()
    {
        ResultInfo resultInfo = new ResultInfo();
        try{
            resultInfo = newsService.queryData();
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }


    /**
     * 新增
     *
     * @param data  数据
     * @param file  图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public ResultInfo  add (@RequestParam(value = "data",required = true)  String data,
                            @RequestParam(value = "file",required = false) MultipartFile file)
    {
        ResultInfo resultInfo = new ResultInfo();
        NewsInfo newsInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            newsInfo = new NewsInfo();
            newsInfo.setCateCode(SysString.getMapStr(mp,"cateCode"));
            newsInfo.setContent(SysString.getMapStr(mp,"content"));
            newsInfo.setDesc(SysString.getMapStr(mp,"desc"));
            newsInfo.setTitile(SysString.getMapStr(mp,"titile"));
            newsInfo.setShow((Boolean)mp.get("show"));
            newsInfo.setHot((Boolean)mp.get("hot"));
            resultInfo = newsService.add(newsInfo,file);

        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
    /**
     * 修改
     *
     * @param data  数据
     * @param file  图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public ResultInfo  edit (@RequestParam(value = "data",required = true)  String data,
                            @RequestParam(value = "file",required = false) MultipartFile file)
    {
        ResultInfo resultInfo = new ResultInfo();
        NewsInfo newsInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            newsInfo = new NewsInfo();
            newsInfo.setID(SysNumber.getMapInt(mp,"ID"));
            newsInfo.setCateCode(SysString.getMapStr(mp,"cateCode"));
            newsInfo.setContent(SysString.getMapStr(mp,"content"));
            newsInfo.setDesc(SysString.getMapStr(mp,"desc"));
            newsInfo.setTitile(SysString.getMapStr(mp,"titile"));
            newsInfo.setShow((Boolean)mp.get("show"));
            newsInfo.setHot((Boolean)mp.get("hot"));
            resultInfo = newsService.edit(newsInfo,file);
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 修改
     *
     * @param ID  序号
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResultInfo  delete (@RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        NewsInfo newsInfo = null;
        try{
            resultInfo = newsService.delete(ID);
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 修改
     *
     * @param ID  序号
     * @return
     */
    @ResponseBody
    @RequestMapping("/get")
    public ResultInfo  get (@RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        NewsInfo newsInfo = null;
        try{
            resultInfo = newsService.get(ID);
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

}
