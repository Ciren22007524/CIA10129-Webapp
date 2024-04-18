package com.chihyunro.mycoupoun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "myCoupoun")
public class MyCoupounVO {
    @Id
    @Column(name = "coupNo")
    private Integer coupNo;
    @Column(name = "memNo")
    private Integer memNo;
    @Column(name = "coupUsedStat")
    private Byte coupUsedStat;
    @Column(name = "coupInfo")
    private String coupInfo;
    @Column(name = "coupExpDate")
    private Timestamp coupExpDate;
}
