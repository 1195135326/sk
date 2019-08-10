package com.ts.system.newsManager.dao;


import com.ts.comm.SysDate;
import com.ts.comm.SysString;
import com.ts.common.Utils;
import com.ts.entity.ResultInfo;
import com.ts.file.SysFile;
import com.ts.jdbc.SysDB;
import com.ts.system.newsManager.UI.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /***
     * 新增
     *
     * @param newsInfo 新闻
     * @param b  图片
     * @return
     */
    @Override
    public ResultInfo add(NewsInfo newsInfo, byte[] b) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iMaxID = 0;
        String sSysPath = "";
        String sPath = "";
        String sPicName = "";
        try{
            sb =  new StringBuffer();
            sb.append(" SELECT COALESCE(MAX(fid),0) FROM s_news ");
            iMaxID = SysDB.getIntValue(jdbcTemplate,sb.toString(),mp);
            iMaxID++;
            //插入
            sb.setLength(0);
            sb.append(" INSERT INTO s_news(fid,fcatecode,ftitile,fdesc,fcontent,fdatetime,fishot,fnoshow,ftime) ");
            sb.append(" VALUES(:fid,:fcatecode,:ftitile,:fdesc,:fcontent,CURRENT_TIMESTAMP,:fishot,:fnoshow,CURRENT_TIMESTAMP)");
            mp.put("fid",iMaxID);
            mp.put("fcatecode",newsInfo.getCateCode());
            mp.put("ftitile",newsInfo.getTitile());
            mp.put("fdesc",newsInfo.getDesc());
            mp.put("fcontent",newsInfo.getContent());
            mp.put("fdatetime", SysDate.getNowDateTime());
            mp.put("fishot",newsInfo.isHot());
            mp.put("fnoshow",newsInfo.isShow());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            //有图片
            if(b!=null)
            {
                sSysPath = Utils.getSysPath();
                if(SysString.isEmpty(sSysPath))
                {
                    resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                }else {
                    sSysPath = sSysPath+"/"+iMaxID+"/";
                    if(new File(sSysPath).exists())
                    {
                        SysFile.deleteDir(sSysPath);
                    }else{
                        SysFile.createDir(sSysPath);
                        sPicName = newsInfo.getPicName();
                        sPath = sSysPath  + sPicName;
                        File file = new File(sPath);
                        OutputStream out = new FileOutputStream(file);
                        out.write(b);
                        out.flush();
                        out.close();
                        sb.setLength(0);
                        mp.clear();
                        sb.append(" UPDATE s_news SET fpicname = :fpicname ,fpicpath=:fpicpath ");
                        sb.append("              WHERE fid = :fid");
                        mp.put("fid", iMaxID);
                        mp.put("fpicname", sPicName);
                        mp.put("fpicpath",sPath);
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
     * 新增
     *
     * @param newsInfo 新闻
     * @param b  图片
     * @return
     */
    @Override
    public ResultInfo edit(NewsInfo newsInfo, byte[] b) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iID = 0;
        String sSysPath = "";
        String sOldPath = "";
        String sPath = "";
        String sPicName = "";
        try{
            sb =  new StringBuffer();
            iID = newsInfo.getID();
            //获取原来的路径
            sb.setLength(0);
            sb.append(" SELECT fpicpath FROM s_news where fid ="+iID);
            sOldPath = SysDB.getStringValue(jdbcTemplate,sb.toString());
            //修改
            sb.setLength(0);
            sb.append("UPDATE s_news s  ");
            sb.append(" SET fcatecode=:fcatecode,ftitile=:ftitile");
            sb.append("     ,fdesc =:fdesc,fcontent =:fcontent,fdatetime = CURRENT_TIMESTAMP ");
            sb.append("     ,fishot =:fishot,fnoshow=:fnoshow,ftime = CURRENT_TIMESTAMP ");
            sb.append("     WHERE s.fid=:fid");
            mp.put("fid",iID);
            mp.put("fcatecode",newsInfo.getCateCode());
            mp.put("ftitile",newsInfo.getTitile());
            mp.put("fdesc",newsInfo.getDesc());
            mp.put("fcontent",newsInfo.getContent());
            mp.put("fishot",newsInfo.isHot());
            mp.put("fnoshow",newsInfo.isShow());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            //有图片
            if(b!=null)
            {
                sSysPath = Utils.getSysPath();
                if(SysString.isEmpty(sSysPath))
                {
                    resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                }else {
                    sSysPath = sSysPath+"/"+iID+"/";
                    if(new File(sSysPath).exists())
                    {
                        SysFile.deleteDir(sSysPath);
                    }
                    SysFile.createDir(sSysPath);
                    sPicName = newsInfo.getPicName();
                    sPath = sSysPath  + sPicName;
                    File file = new File(sPath);
                    OutputStream out = new FileOutputStream(file);
                    out.write(b);
                    out.flush();
                    out.close();
                    sb.setLength(0);
                    mp.clear();
                    sb.append(" UPDATE s_news SET fpicname = :fpicname ,fpicpath=:fpicpath ");
                    sb.append("              WHERE fid = :fid");
                    mp.put("fid", iID);
                    mp.put("fpicname", sPicName);
                    mp.put("fpicpath",sPath);
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
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

//    public ResultInfo add(NewsInfo newsInfo, byte[] b) {
//        ResultInfo resultInfo =  new ResultInfo();
//        HashMap<String,Object> mp = new HashMap<>();
//        StringBuffer sb = null;
//        int iMaxID = 0;
//        String sSysPath = "";
//        try{
//            sb =  new StringBuffer();
//            sb.append(" SELECT COALESCE(MAX(fid),0) FROM s_news ");
//            iMaxID = SysDB.getIntValue(jdbcTemplate,sb.toString(),mp);
//            iMaxID++;
//            //插入
//            sb.setLength(0);
//            sb.append(" INSERT INTO s_news(fid,fcatecode,ftitile,fdesc,fcontent,fdatetime,fishot,fnoshow,ftime) ");
//            sb.append(" VALUES(:fid,:fcatecode,:ftitile,:fdesc,:fcontent,CURRENT_TIMESTAMP,:fishot,:fnoshow,CURRENT_TIMESTAMP)");
//            mp.put("fid",iMaxID);
//            mp.put("fcatecode",newsInfo.getCateCode());
//            mp.put("ftitile",newsInfo.getTitile());
//            mp.put("fdesc",newsInfo.getDesc());
//            mp.put("fcontent",newsInfo.getContent());
//            mp.put("fishot",newsInfo.isHot());
//            mp.put("fnoshow",newsInfo.isShow());
//            SysDB.update(jdbcTemplate,sb.toString(),mp);
//            //有图片
//            mp.clear();
//            sb.setLength(0);
//            sb.append(" UPDATE s_news SET fpicname = :fpicname ,fpic=:fpic ");
//            sb.append("              WHERE fid = :fid");
//            mp.put("fid", iMaxID);
//            mp.put("fpicname", newsInfo.getPicName());
//            mp.put("fpic",b);
//            SysDB.update(jdbcTemplate, sb.toString(), mp);
//        }catch (Exception e) {
//            SysFile.deleteDir(sSysPath);
//            resultInfo.setsErrorMsg(e.getLocalizedMessage());
//        }
//        return resultInfo;
//    }
//
//    /***
//     *
//     *
//     * @param newsInfo  新闻
//     * @param b 图片
//     * @return
//     */
//    @Transactional
//    @Override
//    public ResultInfo edit(NewsInfo newsInfo, byte[] b) {
//        ResultInfo resultInfo =  new ResultInfo();
//        HashMap<String,Object> mp = new HashMap<>();
//        StringBuffer sb = null;
//        int iID = 0;
//        String sSysPath = "";
//        String sPath = "";
//        try{
//            sb =  new StringBuffer();
//            //获取原来的路径
//            iID = newsInfo.getID();
//            //修改
//            sb.append("UPDATE s_news s  ");
//            sb.append(" SET fcatecode=:fcatecode,ftitile=:ftitile");
//            sb.append("     ,fdesc =:fdesc,fcontent =:fcontent,fdatetime = CURRENT_TIMESTAMP ");
//            sb.append("     ,fishot =:fishot,fnoshow=:fnoshow,ftime = CURRENT_TIMESTAMP ");
//            sb.append("     WHERE s.fid=:fid");
//            mp.put("fid",iID);
//            mp.put("fcatecode",newsInfo.getCateCode());
//            mp.put("ftitile",newsInfo.getTitile());
//            mp.put("fdesc",newsInfo.getDesc());
//            mp.put("fcontent",newsInfo.getContent());
//            mp.put("fishot",newsInfo.isHot());
//            mp.put("fnoshow",newsInfo.isShow());
//            SysDB.update(jdbcTemplate,sb.toString(),mp);
//            //有图片
//            mp.clear();
//            sb.setLength(0);
//            sb.append(" UPDATE s_news SET fpicname = :fpicname ,fpic=:fpic ");
//            sb.append("              WHERE fid = :fid");
//            mp.put("fid", iID);
//            mp.put("fpicname", newsInfo.getPicName());
//            mp.put("fpic",b);
//            SysDB.update(jdbcTemplate, sb.toString(), mp);
//        }catch (Exception e) {
//            SysFile.deleteDir(sSysPath);
//            resultInfo.setsErrorMsg(e.getLocalizedMessage());
//        }
//        return resultInfo;
//    }

    /**
     *
     * @param iID 新闻ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        String sSysPath = "";
        try{
            sb =  new StringBuffer();
            //修改
            sb.append("DELETE  FROM s_news s  ");
            sb.append("     WHERE s.fid=:fid");
            mp.put("fid",iID);
            SysDB.update(jdbcTemplate,sb.toString(),mp);
        }catch (Exception e) {
            SysFile.deleteDir(sSysPath);
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     *
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
            //修改
            sb.append(" SELECT fid,fcatecode,ftitile,fdesc,fcontent,TO_CHAR(fdatetime,'yyyy-mm-dd hh24:mi:ss') fdatetime ");
            sb.append("         ,fishot,fnoshow,ftime,fpicname FROM s_news ");
            list = SysDB.getRows(jdbcTemplate,sb.toString());
            resultInfo.setRows(list);
            resultInfo.setTotal(list.size());
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
        HashMap<String,Object> mp = new HashMap<>();
        Map<String,Object> mpInfo = new HashMap<>();
        StringBuffer sb = null;
        String sSysPath = "";
        try{
            sb =  new StringBuffer();
            //查询数据
            sb.append(" SELECT fid,fcatecode,ftitile,fdesc,fcontent,TO_CHAR(fdatetime,'yyyy-mm-dd hh24:mi:ss') fdatetime ");
            sb.append("         ,fishot,fnoshow,ftime,fpicname,fpicpath FROM s_news ");
            sb.append(" WHERE  fid=:fid");
            mp.put("fid",iID);
            mpInfo = SysDB.getMapValue(jdbcTemplate,sb.toString(),mp);
            resultInfo.setMpInfo(mpInfo);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

//    /**
//     * 查看
//     *
//     * @param iID 新闻ID
//     * @return
//     */
//    @Override
//    public ResultInfo get(int iID) {
//        ResultInfo resultInfo =  new ResultInfo();
//        HashMap<String,Object> mp = new HashMap<>();
//        Map<String,Object> mpInfo = new HashMap<>();
//        StringBuffer sb = null;
//        String sSysPath = "";
//        try{
//            sb =  new StringBuffer();
//            //查询数据
//            sb.append(" SELECT fid,fcatecode,ftitile,fdesc,fcontent,TO_CHAR(fdatetime,'yyyy-mm-dd hh24:mi:ss') fdatetime ");
//            sb.append("         ,fishot,fnoshow,ftime,fpicname FROM s_news ");
//            sb.append(" WHERE  fid=:fid");
//            mp.put("fid",iID);
//            mpInfo = SysDB.getMapValue(jdbcTemplate,sb.toString(),mp);
//            resultInfo.setMpInfo(mpInfo);
//            sb.setLength(0);
//            sb.append(" SELECT fpic,fpicname FROM s_news WHERE fid=:fid");
//            SqlRowSet rs = SysDB.getResultSet(jdbcTemplate, sb.toString(), mp);
//            HashMap rtMap = new HashMap();
//            if (rs.next()) {
//                byte[] b = (byte[]) rs.getObject("fpic");
//                String fileName = rs.getString("fpicname");
//                rtMap.put("fileName", fileName);
//                rtMap.put("fileData", b);
//            }
//            resultInfo.setObj(rtMap);
//        }catch (Exception e) {
//            resultInfo.setsErrorMsg(e.getLocalizedMessage());
//        }
//        return resultInfo;
//    }
}
