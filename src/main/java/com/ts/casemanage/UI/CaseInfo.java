package com.ts.casemanage.UI;

/**
 * Created by slq on 2019/8/11.
 */
public class CaseInfo {
    /**案例ID*/
    private int ID;
    /**类型*/
    private String cateCode;
    /**标题*/
    private  String titile;
    /*路径**/
    private String picPath;
    /*图片名称**/
    private String picname;
    /*内容**/
    private String conten;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }
}
