package com.ts.system.CaseManager.dao;

import com.ts.comm.SysDate;
import com.ts.comm.SysString;
import com.ts.common.Utils;
import com.ts.entity.ResultInfo;
import com.ts.file.SysFile;
import com.ts.jdbc.SysDB;
import com.ts.system.CaseManager.UI.CaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slq on 2019/8/11.
 */
@Component
public class CaseDaoImpl implements CaseDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /***
     * 新增
     *
     * @param caseInfo 案例
     * @param b  图片
     * @return
     */
    @Override
    public ResultInfo add(CaseInfo caseInfo, byte[] b) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iMaxID = 0;
        String sSysPath = "";
        String sPath = "";
        String sPicName = "";
        try{
            sb =  new StringBuffer();
            sb.append(" SELECT COALESCE(MAX(fid),0) FROM s_case ");
            iMaxID = SysDB.getIntValue(jdbcTemplate,sb.toString(),mp);
            iMaxID++;
            //插入
            sb.setLength(0);
            sb.append(" INSERT INTO s_case(fid,fcatecode,ftitile,fcontent,ftime) ");
            sb.append(" VALUES(:fid,:fcatecode,:ftitile,:fcontent,CURRENT_TIMESTAMP)");
            mp.put("fid",iMaxID);
            mp.put("fcatecode",caseInfo.getCateCode());
            mp.put("ftitile",caseInfo.getTitile());
            mp.put("fcontent",caseInfo.getConten());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            //有图片
            if(b!=null)
            {
                sSysPath = Utils.getSysPath();
                if(SysString.isEmpty(sSysPath))
                {
                    resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                }else {
                    sSysPath = sSysPath+"/news/";
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
                        sPicName = caseInfo.getPicname();
                        sPath = sSysPath  + sPicName;
                        File file = new File(sPath);
                        OutputStream out = new FileOutputStream(file);
                        out.write(b);
                        out.flush();
                        out.close();
                        sb.setLength(0);
                        mp.clear();
                        sb.append(" UPDATE s_case SET fpicname = :fpicname ,fpicpath=:fpicpath ");
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
     * 修改
     *
     * @param caseInfo  案例
     * @param b 图片
     * @return
     */
    @Override
    public ResultInfo edit(CaseInfo caseInfo, byte[] b) {
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
            iID = caseInfo.getID();
            //获取原来的路径
            sb.setLength(0);
            sb.append(" SELECT fpicpath FROM s_case where fid ="+iID);
            sOldPath = SysDB.getStringValue(jdbcTemplate,sb.toString());
            //修改
            sb.setLength(0);
            sb.append("UPDATE s_case s  ");
            sb.append(" SET fcatecode=:fcatecode,ftitile=:ftitile");
            sb.append("     ,fcontent =:fcontent ");
            sb.append("     ,ftime = CURRENT_TIMESTAMP ");
            sb.append("     WHERE s.fid=:fid");
            mp.put("fid",iID);
            mp.put("fcatecode",caseInfo.getCateCode());
            mp.put("ftitile",caseInfo.getTitile());
            mp.put("fcontent",caseInfo.getConten());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
            //有图片
            if(b!=null)
            {
                sSysPath = Utils.getSysPath();
                if(SysString.isEmpty(sSysPath))
                {
                    resultInfo.setsErrorMsg("配置文件未找到有效资源目录配置！");
                }else {
                    sSysPath = sSysPath+"/case/";
                    if(!new File(sSysPath).exists())
                    {
                        SysFile.createDir(sSysPath);
                    }
                    sSysPath = sSysPath+"/"+iID+"/";
                    if(new File(sSysPath).exists())
                    {
                        SysFile.deleteDir(sSysPath);
                    }
                    SysFile.createDir(sSysPath);
                    sPicName = caseInfo.getPicname();
                    sPath = sSysPath  + sPicName;
                    File file = new File(sPath);
                    OutputStream out = new FileOutputStream(file);
                    out.write(b);
                    out.flush();
                    out.close();
                    sb.setLength(0);
                    mp.clear();
                    sb.append(" UPDATE s_case SET fpicname = :fpicname ,fpicpath=:fpicpath ");
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
        return resultInfo;    }

    /***
     * 删除
     *
     * @param iID 案例ID
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
            sb.append(" SELECT fpicpath FROM s_case where fid ="+iID);
            sPath = SysDB.getStringValue(jdbcTemplate,sb.toString());
            //修改
            sb.setLength(0);
            sb.append("DELETE  FROM s_case s  ");
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
            //修改
            sb.append(" SELECT fid,fcatecode,ftitile,fpicpath,fcontent,fpicname,TO_CHAR(ftime,'yyyy-mm-dd hh24:mi:ss') ftime ");
            sb.append("         FROM s_case ");
            list = SysDB.getRows(jdbcTemplate,sb.toString());
            resultInfo.setRows(list);
            resultInfo.setTotal(list.size());
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 查看
     *
     * @param iID 案例ID
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
            sb.append(" SELECT fid,fcatecode,ftitile,fpicpath,fcontent,fpicname,TO_CHAR(ftime,'yyyy-mm-dd hh24:mi:ss') ftime ");
            sb.append("        FROM s_case ");
            sb.append(" WHERE  fid=:fid");
            mp.put("fid",iID);
            mpInfo = SysDB.getMapValue(jdbcTemplate,sb.toString(),mp);
            resultInfo.setMpInfo(mpInfo);
            //查询文件
            if(mpInfo!=null)
            {
                //查询文件
                sPath = SysString.getMapStr(mpInfo,"fpicpath");
                String sPicName = SysString.getMapStr(mpInfo,"fpicname");
                byte []  b = SysFile.readFile(sPath);
                mp.clear();
                mp.put("name",sPicName);
                mp.put("bytedata",b);
                resultInfo.setObj(mp);
            }
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
