package com.ts.system.producManager.control;

import com.ts.comm.SysJson;
import com.ts.comm.SysNumber;
import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.system.newsManager.UI.NewsInfo;
import com.ts.system.producManager.UI.ProductInfo;
import com.ts.system.producManager.service.ProductService;
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
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;


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
            resultInfo = productService.queryData();
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
        ProductInfo productInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            productInfo = new ProductInfo();
            productInfo.setCateCode(SysString.getMapStr(mp,"catecode"));
            productInfo.setContent(SysString.getMapStr(mp,"content"));
            productInfo.setPulishDate(SysString.getMapStr(mp,"publishdate"));
            productInfo.setUrl(SysString.getMapStr(mp,"url"));
            productInfo.setName(SysString.getMapStr(mp,"name"));

            resultInfo = productService.add(productInfo,file);

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
        ProductInfo productInfo = null;
        try{
            HashMap<String,Object> mp = SysJson.toHashMap(data);
            productInfo = new ProductInfo();
            productInfo.setID(SysNumber.getMapInt(mp,"ID"));
            productInfo.setResourceName(SysString.getMapStr(mp,"resourcename"));
            productInfo.setCateCode(SysString.getMapStr(mp,"catecode"));
            productInfo.setContent(SysString.getMapStr(mp,"content"));
            productInfo.setPulishDate(SysString.getMapStr(mp,"publishdate"));
            productInfo.setResourcePath(SysString.getMapStr(mp,"resourcepath"));
            productInfo.setUrl(SysString.getMapStr(mp,"url"));
            productInfo.setName(SysString.getMapStr(mp,"name"));
            resultInfo = productService.edit(productInfo,file);

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
            resultInfo = productService.delete(ID);
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
    public ResultInfo  get (HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        ServletOutputStream outputStream = null;
        try{
            resultInfo = productService.get(ID);
            String fileName = SysString.toString(resultInfo.getMpInfo().get("fresourcename"));
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "iso-8859-1"));
            outputStream = response.getOutputStream();
            if (resultInfo.getObj()!=null) {
                byte [] b = (byte[]) resultInfo.getObj();
                outputStream.write(b);
                outputStream.flush();
                outputStream.close();
            }
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
    @RequestMapping("/downResource")
    public ResultInfo  downResource (HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "ID",required = true)  int ID)
    {
        ResultInfo resultInfo = new ResultInfo();
        ServletOutputStream outputStream = null;
        try{
            resultInfo = productService.downResource(ID);
            String fileName = SysString.toString(resultInfo.getMpInfo().get("fresourcename"));
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "iso-8859-1"));
            outputStream = response.getOutputStream();
            if (resultInfo.getObj()!=null) {
                byte [] b = (byte[]) resultInfo.getObj();
                outputStream.write(b);
                outputStream.flush();
                outputStream.close();
            } else {
                resultInfo.setsErrorMsg("该产品在无下载资源!");
                return resultInfo;
            }
        }catch (Exception e)
        {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }








}
