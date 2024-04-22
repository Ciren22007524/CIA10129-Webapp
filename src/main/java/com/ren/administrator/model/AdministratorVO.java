package com.ren.administrator.model;

import com.ren.title.model.TitleVO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Administrator")
public class AdministratorVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admNo")
    private Integer admNo;
    @Column(name = "admPwd")
    private String admPwd;
    @Column(name = "admName")
    private String admName;
    @Column(name = "admStat")
    private Byte admStat;
    @Column(name = "admEmail")
    private String admEmail;
    @ManyToOne
    @JoinColumn(name = "titleNo", referencedColumnName = "titleNo")
    private TitleVO titleVO;
    @Column(name = "admHireDate")
    private Date admHireDate;
    @Column(name = "admPhoto")
    private byte[] admPhoto;
    @OneToMany(mappedBy = "StudioOrder", cascade = CascadeType.ALL)
    private Set<AdministratorVO> studioOrders;
    @OneToMany(mappedBy = "Title", cascade = CascadeType.ALL)
    private Set<AdministratorVO> titles;
    @OneToMany(mappedBy = "Member", cascade = CascadeType.ALL)
    private Set<AdministratorVO> members;
    @OneToMany(mappedBy = "ColumnArticle", cascade = CascadeType.ALL)
    private Set<AdministratorVO> columnArticles;

    public AdministratorVO() {
    }

    public AdministratorVO(Integer admNo, String admPwd, String admName, Byte admStat, String admEmail, TitleVO titleVO, Date admHireDate, byte[] admPhoto, Set<AdministratorVO> studioOrders, Set<AdministratorVO> titles, Set<AdministratorVO> members, Set<AdministratorVO> columnArticles) {
        this.admNo = admNo;
        this.admPwd = admPwd;
        this.admName = admName;
        this.admStat = admStat;
        this.admEmail = admEmail;
        this.titleVO = titleVO;
        this.admHireDate = admHireDate;
        this.admPhoto = admPhoto;
        this.studioOrders = studioOrders;
        this.titles = titles;
        this.members = members;
        this.columnArticles = columnArticles;
    }

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

    public TitleVO getTitleVO() {
        return titleVO;
    }

    public void setTitleVO(TitleVO titleVO) {
        this.titleVO = titleVO;
    }

    public Date getAdmHireDate() {
        return admHireDate;
    }

    public void setAdmHireDate(Date admHireDate) {
        this.admHireDate = admHireDate;
    }

    public byte[] getAdmPhoto() {
        return admPhoto;
    }

    public void setAdmPhoto(byte[] admPhoto) {
        this.admPhoto = admPhoto;
    }

    public Set<AdministratorVO> getStudioOrders() {
        return studioOrders;
    }

    public void setStudioOrders(Set<AdministratorVO> studioOrders) {
        this.studioOrders = studioOrders;
    }

    public Set<AdministratorVO> getTitles() {
        return titles;
    }

    public void setTitles(Set<AdministratorVO> titles) {
        this.titles = titles;
    }

    public Set<AdministratorVO> getMembers() {
        return members;
    }

    public void setMembers(Set<AdministratorVO> members) {
        this.members = members;
    }

    public Set<AdministratorVO> getColumnArticles() {
        return columnArticles;
    }

    public void setColumnArticles(Set<AdministratorVO> columnArticles) {
        this.columnArticles = columnArticles;
    }

}
