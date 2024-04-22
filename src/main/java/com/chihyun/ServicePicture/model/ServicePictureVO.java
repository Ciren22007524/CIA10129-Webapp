package com.chihyun.ServicePicture.model;

import com.chihyun.ServiceRecord.model.ServiceRecordVO;

import javax.persistence.*;

@Entity
@Table(name = "ServicePicture")
public class ServicePictureVO {
    @Id
    private Integer servicePicNo;

//    private Integer recordNo;

    @Column(columnDefinition = "longblob")
    private Byte[] servicePic;

    @ManyToOne
    @JoinColumn(name = "recordNo", referencedColumnName = "recordNo")
    private ServiceRecordVO serviceRecordVO;

    public Integer getServicePicNo() {
        return servicePicNo;
    }

    public void setServicePicNo(Integer servicePicNo) {
        this.servicePicNo = servicePicNo;
    }

//    public Integer getRecordNo() {
//        return recordNo;
//    }

//    public void setRecordNo(Integer recordNo) {
//        this.recordNo = recordNo;
//    }

    public Byte[] getServicePic() {
        return servicePic;
    }

    public void setServicePic(Byte[] servicePic) {
        this.servicePic = servicePic;
    }
}
