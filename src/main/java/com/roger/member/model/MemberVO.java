package com.roger.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "member")
public class MemberVO {
    @Id
    @Column(name = "memNo")
    private Integer memNo;
    @Column(name = "mName")
    private String mName;
    @Column(name = "memAcc")
    private String memAcc;
    @Column(name = "memPwd")
    private String memPwd;
    @Column(name = "memMob")
    private String memMob;
    @Column(name = "mGender")
    private Byte mGender;
    @Column(name = "memMail")
    private String memMail;
    @Column(name = "memAdd")
    private String memAdd;
    @Column(name = "memBd")
    private Date memBd;
    @Column(name = "memCard")
    private String memCard;
    @Column(name = "provider")
    private Byte provider;
    @Column(name = "clientID")
    private String clientID;
    @Column(name = "displayName")
    private String displayName;
    @Column(name = "accessToken")
    private String accessToken;
    @Column(name = "refreshToken")
    private String refreshToken;
    @Column(name = "tknExpireTime")
    private Timestamp tknExpireTime;
    @Column(name = "creationTime")
    private Timestamp creationTime;
    @Column(name = "memberJoinTime")
    private Timestamp memberJoinTime;
    @Column(name = "memStat")
    private Byte memStat;

    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMemAcc() {
        return memAcc;
    }

    public void setMemAcc(String memAcc) {
        this.memAcc = memAcc;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemMob() {
        return memMob;
    }

    public void setMemMob(String memMob) {
        this.memMob = memMob;
    }

    public Byte getmGender() {
        return mGender;
    }

    public void setmGender(Byte mGender) {
        this.mGender = mGender;
    }

    public String getMemMail() {
        return memMail;
    }

    public void setMemMail(String memMail) {
        this.memMail = memMail;
    }

    public String getMemAdd() {
        return memAdd;
    }

    public void setMemAdd(String memAdd) {
        this.memAdd = memAdd;
    }

    public Date getMemBd() {
        return memBd;
    }

    public void setMemBd(Date memBd) {
        this.memBd = memBd;
    }

    public String getMemCard() {
        return memCard;
    }

    public void setMemCard(String memCard) {
        this.memCard = memCard;
    }

    public Byte getProvider() {
        return provider;
    }

    public void setProvider(Byte provider) {
        this.provider = provider;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Timestamp getTknExpireTime() {
        return tknExpireTime;
    }

    public void setTknExpireTime(Timestamp tknExpireTime) {
        this.tknExpireTime = tknExpireTime;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getMemberJoinTime() {
        return memberJoinTime;
    }

    public void setMemberJoinTime(Timestamp memberJoinTime) {
        this.memberJoinTime = memberJoinTime;
    }

    public Byte getMemStat() {
        return memStat;
    }

    public void setMemStat(Byte memStat) {
        this.memStat = memStat;
    }
}
