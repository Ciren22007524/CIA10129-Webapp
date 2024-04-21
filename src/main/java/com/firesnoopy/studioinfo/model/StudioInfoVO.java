package com.firesnoopy.studioinfo.model;


import com.ren.product.model.ProductVO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "StudioInfo")
public class StudioInfoVO {
    @Id
    @Column(name = "sNo")
    private Integer sNo;
    @Column(name = "sName")
    private String sName;
    @Column(name = "sInfo")
    private String sInfo;
    @Column(name = "sPrice")
    private BigDecimal sPrice;
    @OneToMany(mappedBy = "StudioOrder", cascade = CascadeType.ALL)
    private Set<StudioInfoVO> studioOrders;
    @OneToMany(mappedBy = "StudioTimeBooking", cascade = CascadeType.ALL)
    private Set<StudioInfoVO> studioTimeBookings;

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    public BigDecimal getsPrice() {
        return sPrice;
    }

    public void setsPrice(BigDecimal sPrice) {
        this.sPrice = sPrice;
    }

    public Set<StudioInfoVO> getStudioOrders() {
        return studioOrders;
    }

    public void setStudioOrders(Set<StudioInfoVO> studioOrders) {
        this.studioOrders = studioOrders;
    }

    public Set<StudioInfoVO> getStudioTimeBookings() {
        return studioTimeBookings;
    }

    public void setStudioTimeBookings(Set<StudioInfoVO> studioTimeBookings) {
        this.studioTimeBookings = studioTimeBookings;
    }
}
