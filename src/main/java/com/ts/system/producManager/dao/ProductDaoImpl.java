package com.ts.system.producManager.dao;

import com.ts.comm.SysDate;
import com.ts.comm.SysString;
import com.ts.common.Utils;
import com.ts.entity.ResultInfo;
import com.ts.file.SysFile;
import com.ts.jdbc.SysDB;
import com.ts.system.producManager.UI.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slq on 2019/8/11.
 */
@Component
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    /***
     * *  新增
     *
     * @param productInfo 产品
     * @param b  图片
     * @return
     */
    @Override
    public ResultInfo add(ProductInfo productInfo, byte[] b) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iMaxID = 0;
        String sSysPath = "";
        String sPath = "";
        String sPicName = "";
        try{
            sb =  new StringBuffer();
            sb.append(" SELECT COALESCE(MAX(fid),0) FROM s_product ");
            iMaxID = SysDB.getIntValue(jdbcTemplate,sb.toString(),mp);
            iMaxID++;
            //插入
            sb.setLength(0);
            sb.append(" INSERT INTO s_product(fid,fcatecode,fname,fpublishdate,fcontent,furl,ftime) ");
            sb.append(" VALUES(:fid,:fcatecode,:fname,:fpublishdate,:fcontent,:furl,CURRENT_TIMESTAMP)");
            mp.put("fid",iMaxID);
            mp.put("fcatecode",productInfo.getCateCode());
            mp.put("fname",productInfo.getName());
            mp.put("fpublishdate",SysDate.getNowDate());
            mp.put("fcontent",productInfo.getContent());
            mp.put("furl",productInfo.getUrl());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            //有图片
            if(b!=null)
            {
                sSysPath = Utils.getSysPath();
                if(SysString.isEmpty(sSysPath))
                {
                    resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                }else {
                    sSysPath = sSysPath+"/product/";
                    if(!new File(sSysPath).exists())
                    {
                        SysFile.createDir(sSysPath);
                    }
                    sSysPath = sSysPath+iMaxID+"/";
                    if(new File(sSysPath).exists())
                    {
                        SysFile.deleteDir(sSysPath);
                    }else{
                        SysFile.createDir(sSysPath);
                        sPicName = productInfo.getResourceName();
                        sPath = sSysPath  + sPicName;
                        File file = new File(sPath);
                        OutputStream out = new FileOutputStream(file);
                        out.write(b);
                        out.flush();
                        out.close();
                        sb.setLength(0);
                        mp.clear();
                        sb.append(" UPDATE s_product SET fresourcename = :fresourcename ,fresourcepath=:fresourcepath ");
                        sb.append("              WHERE fid = :fid");
                        mp.put("fid", iMaxID);
                        mp.put("fresourcename",productInfo.getResourceName());
                        mp.put("fresourcepath",sPath);
                        SysDB.update(jdbcTemplate, sb.toString(), mp);
                    }
                }
            }
        }catch (Exception e) {
            SysFile.deleteDir(sSysPath);
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 修改
     *
     * @param productInfo  产品
     * @param b 图片
     * @return
     */
    @Override
    public ResultInfo edit(ProductInfo productInfo, byte[] b) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iID = 0;
        String sOldPath = "";
        String sSysPath = "";
        String sPath = "";
        String sPicName = "";
        try{
            iID = productInfo.getID();
            sb = new StringBuffer();
            //获取原来的路径
            sb.setLength(0);
            sb.append(" SELECT fresourcepath FROM s_product where fid ="+iID);
            sOldPath = SysDB.getStringValue(jdbcTemplate,sb.toString());
            //插入
            sb.setLength(0);
            sb.append(" update s_product SET fcatecode=:fcatecode,fname=:fname,fpublishdate=:fpublishdate ");
            sb.append("         ,fcontent =:fcontent,furl=:furl,ftime = CURRENT_TIMESTAMP ");
            sb.append("              WHERE fid = :fid");
            mp.put("fid",iID);
            mp.put("fcatecode",productInfo.getCateCode());
            mp.put("fname",productInfo.getName());
            mp.put("fpublishdate",SysDate.getNowDate());
            mp.put("fcontent",productInfo.getContent());
            mp.put("furl",productInfo.getUrl());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
                //有图片
                if(b!=null)
                {

                    sSysPath = Utils.getSysPath();
                    if(SysString.isEmpty(sSysPath))
                    {
                        resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                    }else {
                        sSysPath = sSysPath+"/product/";
                        if(!new File(sSysPath).exists())
                        {
                            SysFile.createDir(sSysPath);
                        }
                        sSysPath = sSysPath+iID+"/";
                        if(new File(sSysPath).exists())
                        {
                            SysFile.deleteDir(sSysPath);
                        }
                        SysFile.createDir(sSysPath);
                        sPicName = productInfo.getResourceName();
                        sPath = sSysPath  + sPicName;
                        File file = new File(sPath);
                        OutputStream out = new FileOutputStream(file);
                        out.write(b);
                        out.flush();
                        out.close();
                        sb.setLength(0);
                        mp.clear();
                        sb.append(" UPDATE s_product SET fresourcename = :fresourcename ,fresourcepath=:fresourcepath ");
                        sb.append("              WHERE fid = :fid");
                        mp.put("fid", iID);
                        mp.put("fresourcename",productInfo.getResourceName());
                        mp.put("fresourcepath",sPath);
                        SysDB.update(jdbcTemplate, sb.toString(), mp);
                    }
                }else{
                    if(!SysString.isEmpty(sOldPath))
                    {
                        if(new File(sOldPath).exists())
                        {
                            SysFile.deleteFile(sOldPath);
                        }
                    }
                }
        }catch (Exception e) {
            SysFile.deleteDir(sSysPath);
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
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        String sPath = "";
        try{
            sb =  new StringBuffer();
            //获取路径，如果有图片，清除图片
            sb.setLength(0);
            sb.append(" SELECT fresourcepath FROM s_product where fid ="+iID);
            sPath = SysDB.getStringValue(jdbcTemplate,sb.toString());
            //修改
            sb.setLength(0);
            sb.append("DELETE  FROM s_product s  ");
            sb.append("     WHERE s.fid=:fid");
            mp.put("fid",iID);
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            if(!SysString.isEmpty(sPath))
            {
                SysFile.deleteFile(sPath);
            }
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
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        String sSysPath = "";
        List<Map<String,Object>> list = null;
        try{
            sb =  new StringBuffer();
            //查询
            sb.append(" SELECT fid,fcatecode,fname,fpublishdate,fcontent ");
            sb.append("         ,furl,fresourcepath,ftime,fresourcename FROM s_product ");
            list = SysDB.getRows(jdbcTemplate,sb.toString());
            resultInfo.setRows(list);
            resultInfo.setTotal(list.size());
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
        HashMap<String,Object> mp = new HashMap<>();
        Map<String,Object> mpInfo = new HashMap<>();
        StringBuffer sb = null;
        String sPath = "";
        try{
            sb =  new StringBuffer();
            //查询数据
            sb.append(" SELECT fid,fcatecode,fname,fpublishdate,fcontent ");
            sb.append("         ,furl,fresourcepath,ftime,fresourcename FROM s_product ");
            sb.append(" WHERE  fid=:fid");
            mp.put("fid",iID);
            mpInfo = SysDB.getMapValue(jdbcTemplate,sb.toString(),mp);
            resultInfo.setMpInfo(mpInfo);
            //查询文件
            sPath = SysString.getMapStr(mpInfo,"fresourcepath");
            String sResourceName = SysString.getMapStr(mpInfo,"fresourcename");
            byte []  b = SysFile.readFile(sPath);
            resultInfo.setObj(b);

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
        HashMap<String,Object> mp = new HashMap<>();
        Map<String,Object> mpInfo = new HashMap<>();
        StringBuffer sb = null;
        String sPath = "";
        try{
            sb =  new StringBuffer();
            //查询数据
            sb.append(" SELECT fid ");
            sb.append("         ,fresourcepath,fresourcename FROM s_product ");
            sb.append(" WHERE  fid=:fid");
            mp.put("fid",iID);
            mpInfo = SysDB.getMapValue(jdbcTemplate,sb.toString(),mp);
            resultInfo.setMpInfo(mpInfo);
            //查询文件
            sPath = SysString.getMapStr(mpInfo,"fresourcepath");
            String sResourceName = SysString.getMapStr(mpInfo,"fresourcename");
            byte []  b = SysFile.readFile(sPath);
            resultInfo.setObj(b);

        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
