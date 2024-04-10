package com.ren.administrator.model;


import java.sql.Date;

public class AdministratorVO {

    private Integer admNo;
    private String admPwd;
    private String admName;
    private Byte admStat;
    private String admEmail;
    private Integer titleNo;
    private Date admHireDate;

    public Integer getAdmNo() {
        return admNo;
    }

    public void setAdmNo(Integer admNo) {
        this.admNo = admNo;
    }

    public String getAdmPwd() {
        return admPwd;
    }

    public void setAdmPwd(String admPwd) {
        this.admPwd = admPwd;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public Byte getAdmStat() {
        return admStat;
    }

    public void setAdmStat(Byte admStat) {
        this.admStat = admStat;
    }

    public String getAdmEmail() {
        return admEmail;
    }

    public void setAdmEmail(String admEmail) {
        this.admEmail = admEmail;
    }

    public Integer getTitleNo() {
        return titleNo;
    }

    public void setTitleNo(Integer titleNo) {
        this.titleNo = titleNo;
    }

    public Date getAdmHireDate() {
        return admHireDate;
    }

    public void setAdmHireDate(Date admHireDate) {
        this.admHireDate = admHireDate;
    }

}
