package com.ts.system.postManager.UI;

import java.util.Date;

public class PostInfo {
    private  String name;
    private  String postname;
    private  String postnumber;
    private  String workadress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String floorsalary;
    private  String topsalary;
    private  String postdesc;
    private  String postdemand;
    private  Date publishdate;
    private  Date lawfuldate ;


    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostnumber() {
        return postnumber;
    }

    public void setPostnumber(String postnumber) {
        this.postnumber = postnumber;
    }

    public String getWorkadress() {
        return workadress;
    }

    public void setWorkadress(String workadress) {
        this.workadress = workadress;
    }

    public String getPostdesc() {
        return postdesc;
    }

    public void setPostdesc(String postdesc) {
        this.postdesc = postdesc;
    }

    public String getFloorsalary() {
        return floorsalary;
    }

    public void setFloorsalary(String floorsalary) {
        this.floorsalary = floorsalary;
    }

    public String getTopsalary() {
        return topsalary;
    }

    public void setTopsalary(String topsalary) {
        this.topsalary = topsalary;
    }

    public String getPostdemand() {
        return postdemand;
    }

    public void setPostdemand(String postdemand) {
        this.postdemand = postdemand;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Date getLawfuldate() {
        return lawfuldate;
    }

    public void setLawfuldate(Date lawfuldate) {
        this.lawfuldate = lawfuldate;
    }


}
