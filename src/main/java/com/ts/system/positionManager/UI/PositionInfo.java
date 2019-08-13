package com.ts.system.positionManager.UI;

import java.security.SecureRandom;

/**
 * Created by slq on 2019/8/13.
 */
public class PositionInfo {
    /**序号*/
    private int ID;
    /**岗位名称*/
    private String name;
    /**工作地区*/
    private String area;
    /**需求数量*/
    private int neednum;
    /**起薪*/
    private double minpay;
    /**最高薪资*/
    private double maxpay;
    /**岗位职责描述*/
    private String postdesc;
    /**职位要求*/
    private String postreq;
    /**发布日期*/
    private String publishdate;
    /**有效日期*/
    private String enddate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNeednum() {
        return neednum;
    }

    public void setNeednum(int neednum) {
        this.neednum = neednum;
    }

    public double getMinpay() {
        return minpay;
    }

    public void setMinpay(double minpay) {
        this.minpay = minpay;
    }

    public double getMaxpay() {
        return maxpay;
    }

    public void setMaxpay(double maxpay) {
        this.maxpay = maxpay;
    }

    public String getPostdesc() {
        return postdesc;
    }

    public void setPostdesc(String postdesc) {
        this.postdesc = postdesc;
    }

    public String getPostreq() {
        return postreq;
    }

    public void setPostreq(String postreq) {
        this.postreq = postreq;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
