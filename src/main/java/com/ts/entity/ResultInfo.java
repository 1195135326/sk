package com.ts.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultInfo {
    private List<?> rows;
    private int total;
    private int pageSize;
    private int pageNum;
    private String sErrorMsg;
    private Object obj;
    private Map<String,Object> mpInfo;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getsErrorMsg() {
        return sErrorMsg;
    }

    public void setsErrorMsg(String sErrorMsg) {
        this.sErrorMsg = sErrorMsg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Map<String, Object> getMpInfo() {
        return mpInfo;
    }

    public void setMpInfo(Map<String, Object> mpInfo) {
        this.mpInfo = mpInfo;
    }
}
