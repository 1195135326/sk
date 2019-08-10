package com.ts.system.newsManager.UI;

/***
 * 新闻实体
 *
 */
public class NewsInfo {
    /**新闻图片*/
    private int ID;
    /**新闻名称*/
    private String titile;
    /**新闻类别*/
    private String cateCode;
    /**新闻内容*/
    private String   content;
    /**发布时间*/
    private String dateTime;
    /**是否热点*/
    private boolean  hot;
    /**是否下架*/
    private boolean  show;
    /**新闻摘要*/
    private String desc;
    /**新闻图片名称*/
    private String picName;
    /**新闻图片*/
    private String path;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}
