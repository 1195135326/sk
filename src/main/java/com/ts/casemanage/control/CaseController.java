package com.ts.casemanage.control;


import com.ts.comm.SysJson;
import com.ts.comm.SysNumber;
import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.casemanage.UI.CaseInfo;
import com.ts.casemanage.service.CaseService;
import com.ts.system.newsManager.UI.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

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
            resultInfo = caseService.queryData();
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
        CaseInfo caseInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            caseInfo = new CaseInfo();
            caseInfo.setCateCode(SysString.getMapStr(mp,"cateCode"));
            caseInfo.setConten(SysString.getMapStr(mp,"content"));
            caseInfo.setTitile(SysString.getMapStr(mp,"titile"));
            resultInfo = caseService.add(caseInfo,file);

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
        CaseInfo caseInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            caseInfo = new CaseInfo();
            caseInfo.setID(SysNumber.getMapInt(mp,"ID"));
            caseInfo.setCateCode(SysString.getMapStr(mp,"cateCode"));
            caseInfo.setConten(SysString.getMapStr(mp,"content"));
            caseInfo.setTitile(SysString.getMapStr(mp,"titile"));
            resultInfo = caseService.edit(caseInfo,file);
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
            resultInfo = caseService.delete(ID);
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
    public ResultInfo  get (HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        ServletOutputStream outputStream = null;
        HashMap<String,Object> mp = null;
        String fileName ="";
        try{
            resultInfo = caseService.get(ID);
            if(resultInfo!=null  && resultInfo.getMpInfo()!=null)
            {
                fileName = SysString.toString(resultInfo.getMpInfo().get("fpicname"));
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "iso-8859-1"));
                outputStream = response.getOutputStream();
                if (resultInfo.getObj()!=null) {
                    mp = ( HashMap<String,Object> )resultInfo.getObj();
                    if(mp!=null)
                    {
                        byte [] b = (byte[]) mp.get("bytedata");
                        outputStream.write(b);
                        outputStream.flush();
                        outputStream.close();
                    }
                }
            }else{
                resultInfo.setsErrorMsg("未获取到有效数据!");
            }
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

}
