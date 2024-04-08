package com.product.model;

import java.math.BigDecimal;

public class ProductVO {
	
    private Integer pNo;
    private Integer pCatNo;
    private String pName;
    private String pInfo;
    private Integer pSize;
    private String pColor;
    private BigDecimal pPrice;
    private Byte pStat;
    private Integer pSalQty;
    private Integer pComPeople;
    private Integer pComScore;

    public Integer getpNo() {
        return pNo;
    }

    public void setpNo(Integer pNo) {
        this.pNo = pNo;
    }

    public Integer getpCatNo() {
        return pCatNo;
    }

    public void setpCatNo(Integer pCatNo) {
        this.pCatNo = pCatNo;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpInfo() {
        return pInfo;
    }

    public void setpInfo(String pInfo) {
        this.pInfo = pInfo;
    }

    public Integer getpSize() {
        return pSize;
    }

    public void setpSize(Integer pSize) {
        this.pSize = pSize;
    }

    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public Byte getpStat() {
        return pStat;
    }

    public void setpStat(Byte pStat) {
        this.pStat = pStat;
    }

    public Integer getpSalQty() {
        return pSalQty;
    }

    public void setpSalQty(Integer pSalQty) {
        this.pSalQty = pSalQty;
    }

    public Integer getpComPeople() {
        return pComPeople;
    }

    public void setpComPeople(Integer pComPeople) {
        this.pComPeople = pComPeople;
    }

    public Integer getpComScore() {
        return pComScore;
    }

    public void setpComScore(Integer pComScore) {
        this.pComScore = pComScore;
    }

}

