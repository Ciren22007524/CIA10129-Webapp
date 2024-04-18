package com.yu.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Rental")
public class RentalVO {
    @Id
    @Column(name = "rNo")
    private Integer rNo;
    @Column(name = "rCatNo")
    private Integer rCatNo;
    @Column(name = "rName")
    private String rName;
    @Column(name = "rPrice")
    private BigDecimal rPrice;
    @Column(name = "rSize")
    private Integer rSize;
    @Column(name = "rColor")
    private String rColor;
    @Column(name = "rInfo")
    private String rInfo;
    @Column(name = "rStat")
    private Byte rStat;

}
