package com.ts.system.positionManager.dao;

import com.ts.comm.SysDate;
import com.ts.comm.SysString;
import com.ts.entity.ResultInfo;
import com.ts.file.SysFile;
import com.ts.jdbc.SysDB;
import com.ts.system.positionManager.UI.PositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slq on 2019/8/13.
 */
@Component
@Transactional
public class PositionDaoImpl implements  PositionDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    /***
     * *  新增
     *
     * @param positionInfo 招聘岗位
     * @return
     */
    @Override
    public ResultInfo add(PositionInfo positionInfo) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iMaxID = 0;
        try{
            sb =  new StringBuffer();
            sb.append(" SELECT COALESCE(MAX(fid),0) FROM s_position ");
            iMaxID = SysDB.getIntValue(jdbcTemplate,sb.toString(),mp);
            iMaxID++;
            //插入
            sb.setLength(0);
            sb.append(" INSERT INTO s_position(fid,fname,farea,fneednum,fminpay,fmaxpay,fpostdesc,fpostreq,fpublishdate,fenddate,ftime) ");
            sb.append(" VALUES(:fid,:fname,:farea,:fneednum,:fminpay,:fmaxpay,:fpostdesc,:fpostreq,:fpublishdate,:fenddate,CURRENT_TIMESTAMP)");
            mp.put("fid",iMaxID);
            mp.put("farea",positionInfo.getArea());
            mp.put("fname",positionInfo.getName());
            mp.put("fpublishdate", positionInfo.getPublishdate());
            mp.put("fneednum",positionInfo.getNeednum());
            mp.put("fminpay",positionInfo.getMinpay());
            mp.put("fmaxpay",positionInfo.getMaxpay());
            mp.put("fpostdesc",positionInfo.getPostdesc());
            mp.put("fpostreq",positionInfo.getPostreq());
            mp.put("fenddate",positionInfo.getEnddate());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }

    /***
     * 修改
     *
     * @param positionInfo  招聘岗位
     * @return
     */
    @Override
    public ResultInfo edit(PositionInfo positionInfo) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        int iID = 0;
        try{
            sb =  new StringBuffer();
            iID = positionInfo.getID();
            //插入
            sb.setLength(0);
            sb.append(" UPDATE s_position  ");
            sb.append("    SET fname=:fname,farea=:farea,fneednum=:fneednum,fminpay=:fminpay,fmaxpay=:fmaxpay");
            sb.append("             ,fpostdesc=:fpostdesc,fpostreq=:fpostreq,fpublishdate=:fpublishdate,fenddate=:fenddate,ftime=CURRENT_TIMESTAMP ");
            sb.append(" WHERE fid=:fid ");
            mp.put("fid",iID);
            mp.put("farea",positionInfo.getArea());
            mp.put("fname",positionInfo.getName());
            mp.put("fpublishdate", positionInfo.getPublishdate());
            mp.put("fneednum",positionInfo.getNeednum());
            mp.put("fminpay",positionInfo.getMinpay());
            mp.put("fmaxpay",positionInfo.getMaxpay());
            mp.put("fpostdesc",positionInfo.getPostdesc());
            mp.put("fpostreq",positionInfo.getPostreq());
            mp.put("fenddate",positionInfo.getEnddate());
            SysDB.update(jdbcTemplate,sb.toString(),mp);
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;    }

    /***
     * 删除
     *
     * @param iID 招聘岗位ID
     * @return
     */
    @Override
    public ResultInfo delete(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        try{
            sb =  new StringBuffer();
            sb.setLength(0);
            sb.append("DELETE  FROM  s  ");
            sb.append("     WHERE s.fid=:fid");
            mp.put("fid",iID);
            SysDB.update(jdbcTemplate,sb.toString(),mp);
        }catch (Exception e) {

            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;    }

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
            sb.append(" SELECT fid,fname,farea,fneednum,fminpay,fmaxpay,fpostdesc,fpostreq,fpublishdate,fenddate ");
            sb.append("   FROM s_position ");
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
     * @param iID 招聘岗位ID
     * @return
     */
    @Override
    public ResultInfo get(int iID) {
        ResultInfo resultInfo =  new ResultInfo();
        HashMap<String,Object> mp = new HashMap<>();
        StringBuffer sb = null;
        String sSysPath = "";
        List<Map<String,Object>> list = null;
        try{
            sb =  new StringBuffer();
            //查询
            sb.append(" SELECT fid,fname,farea,fneednum,fminpay,fmaxpay,fpostdesc,fpostreq,fpublishdate,fenddate ");
            sb.append("   FROM s_position ");
            sb.append("   WHERE fid=:fid ");
            mp.put("fid",iID);
            resultInfo.setMpInfo(SysDB.getMapValue(jdbcTemplate,sb.toString(),mp));
        }catch (Exception e) {
            resultInfo.setsErrorMsg(e.getLocalizedMessage());
        }
        return resultInfo;
    }
}
