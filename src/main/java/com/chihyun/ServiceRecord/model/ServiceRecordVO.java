package com.chihyun.ServiceRecord.model;

import com.chihyun.ServicePicture.model.ServicePictureVO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "ServiceRecord")
public class ServiceRecordVO {
	@Id
    @Column(name = "recordNo")
	private Integer recordNo;
    @Column(name = "admNo")
    private Integer admNo;
    @Column(name = "memNo")
    private Integer memNo;
    @Column(name = "recordTime")
    private Date recordTime;
    @Column(name = "recordContent")
    private String recordContent;
    @Column(name = "speaker")
    private Integer speaker;

    @OneToMany(mappedBy = "serviceRecordVO")
    @OrderBy("servicePicNo asc")
    private Set<ServicePictureVO> ServicePictures;

    public Integer getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(Integer recordNo) {
        this.recordNo = recordNo;
    }

    public Integer getAdmNo() {
        return admNo;
    }

    public void setAdmNo(Integer admNo) {
        this.admNo = admNo;
    }

    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Integer getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Integer speaker) {
        this.speaker = speaker;
    }
}
