package com.chihyun.Coupon.model;

import com.chihyun.Mycoupon.model.MycouponVO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Coupon")
public class CouponVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coupNo;
    private String coupName;
    private String coupCond;
    private Double coupDisc;
    private Date coupAddDate;
    private Date coupExpDate;
    private Date coupRelDate;
    private Integer coupRealStat;

    @OneToMany(mappedBy = "couponVO", cascade = CascadeType.ALL)
    @OrderBy("coupNo asc")
    private Set<MycouponVO> mycoupons;


    public Integer getCoupNo() {
        return coupNo;
    }

    public void setCoupNo(Integer coupNo) {
        this.coupNo = coupNo;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public String getCoupCond() {
        return coupCond;
    }

    public void setCoupCond(String coupCond) {
        this.coupCond = coupCond;
    }

    public Double getCoupDisc() {
        return coupDisc;
    }

    public void setCoupDisc(Double coupDisc) {
        this.coupDisc = coupDisc;
    }

    public Date getCoupAddDate() {
        return coupAddDate;
    }

    public void setCoupAddDate(Date coupAddDate) {
        this.coupAddDate = coupAddDate;
    }

    public Date getCoupExpDate() {
        return coupExpDate;
    }

    public void setCoupExpDate(Date coupExpDate) {
        this.coupExpDate = coupExpDate;
    }

    public Date getCoupRelDate() {
        return coupRelDate;
    }

    public void setCoupRelDate(Date coupRelDate) {
        this.coupRelDate = coupRelDate;
    }

    public Integer getCoupRealStat() {
        return coupRealStat;
    }

    public void setCoupRealStat(Integer coupRealStat) {
        this.coupRealStat = coupRealStat;
    }
}
