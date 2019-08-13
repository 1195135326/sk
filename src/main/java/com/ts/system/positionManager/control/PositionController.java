package com.ts.system.positionManager.control;

import com.ts.comm.SysJson;
import com.ts.comm.SysNumber;
import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.system.positionManager.UI.PositionInfo;
import com.ts.system.positionManager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by slq on 2019/8/13.
 */
@Controller
@RequestMapping(value = "/position")
public class PositionController {


    @Autowired
    private PositionService positionService;


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
            resultInfo = positionService.queryData();
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
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public ResultInfo  add (@RequestParam(value = "data",required = true)  String data)
    {
        ResultInfo resultInfo = new ResultInfo();
        PositionInfo positionInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            positionInfo = new PositionInfo();
            positionInfo.setArea(SysString.getMapStr(mp,"area"));
            positionInfo.setEnddate(SysString.getMapStr(mp,"enddate"));
            positionInfo.setMaxpay(SysNumber.getMapDouble(mp,"maxpay"));
            positionInfo.setMinpay(SysNumber.getMapDouble(mp,"minpay"));
            positionInfo.setNeednum(SysNumber.getMapInt(mp,"neednum"));
            positionInfo.setName(SysString.getMapStr(mp,"name"));
            positionInfo.setPostdesc(SysString.getMapStr(mp,"postdesc"));
            positionInfo.setPostreq(SysString.getMapStr(mp,"postreq"));
            positionInfo.setPublishdate(SysString.getMapStr(mp,"publishdate"));
            resultInfo = positionService.add(positionInfo);

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
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public ResultInfo  edit (@RequestParam(value = "data",required = true)  String data)
    {
        ResultInfo resultInfo = new ResultInfo();
        PositionInfo positionInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            positionInfo = new PositionInfo();
            positionInfo.setID(SysNumber.getMapInt(mp,"ID"));
            positionInfo.setArea(SysString.getMapStr(mp,"area"));
            positionInfo.setEnddate(SysString.getMapStr(mp,"enddate"));
            positionInfo.setMaxpay(SysNumber.getMapDouble(mp,"maxpay"));
            positionInfo.setMinpay(SysNumber.getMapDouble(mp,"minpay"));
            positionInfo.setNeednum(SysNumber.getMapInt(mp,"neednum"));
            positionInfo.setName(SysString.getMapStr(mp,"name"));
            positionInfo.setPostdesc(SysString.getMapStr(mp,"postdesc"));
            positionInfo.setPostreq(SysString.getMapStr(mp,"postreq"));
            positionInfo.setPublishdate(SysString.getMapStr(mp,"publishdate"));
            resultInfo = positionService.edit(positionInfo);

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
        try{
            resultInfo = positionService.delete(ID);
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /**
     * 查看
     *
     * @param ID  序号
     * @return
     */
    @ResponseBody
    @RequestMapping("/get")
    public ResultInfo  get (  @RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        ServletOutputStream outputStream = null;
        HashMap<String,Object> mp = null;
        String fileName ="";
        try{
            resultInfo = positionService.get(ID);
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
