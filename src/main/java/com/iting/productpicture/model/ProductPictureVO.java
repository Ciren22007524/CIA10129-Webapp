package com.iting.productpicture.model;

import com.ren.product.model.ProductVO;

import javax.persistence.*;


@Entity
@Table(name = "productpicture")
public class ProductPictureVO implements java.io.Serializable {
    @Id
    @Column(name = "pPicNo", updatable = false)
    private Integer pPicNo;
    @ManyToOne
    @JoinColumn(name = "pNo", referencedColumnName = "pNo")
    private ProductVO productVO;
    @Column(name = "pPic", columnDefinition = "longblob")
    private byte[] pPic;

    public Integer getpPicNo() {
        return pPicNo;
    }

    public void setpPicNo(Integer pPicNo) {
        this.pPicNo = pPicNo;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public byte[] getpPic() {
        return pPic;
    }

    public void setpPic(byte[] pPic) {
        this.pPic = pPic;
    }

}
