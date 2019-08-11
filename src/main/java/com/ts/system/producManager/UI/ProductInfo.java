package com.ts.system.producManager.UI;

/**
 * Created by slq on 2019/8/11.
 */
public class ProductInfo {
    /**序号*/
    private int ID;
    /**类别*/
    private String cateCode;
    /**名称*/
    private String name;
    /**发布日期**/
    private  String pulishDate;
    /**产品描述*/
    private String content;
    /***演示网址**/
    private String url;

    /**安装包名称*/
    private String resourceName;
    /**安装包地址**/
    private String resourcePath;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPulishDate() {
        return pulishDate;
    }

    public void setPulishDate(String pulishDate) {
        this.pulishDate = pulishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
