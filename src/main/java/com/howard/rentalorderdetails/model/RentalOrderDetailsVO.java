package com.howard.rentalorderdetails.model;

import com.howard.rentalorder.model.RentalOrderVO;
import com.yu.rental.model.RentalVO;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rentalorderdetails")
@IdClass(RentalOrderDetailsVO.CompositeDetail.class)
public class RentalOrderDetailsVO implements Serializable{

    public RentalOrderDetailsVO() {

    }

    /*
     * rOrdNo -> 租借品訂單編號
     * rNo -> 租借品編號
     * rPrice -> 單價
     * rDesPrice -> 押金(單件)
     */

//    @Id
//    @Column(name = "rOrdNo")
//    private Integer rOrdNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rOrdNo", referencedColumnName = "rOrdNo")
    private RentalOrderVO rentalOrderVoOrm;

//    @Id
//    @Column(name = "rNo")
//    private Integer rNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rNo", referencedColumnName = "rNo")
    private RentalVO rentalVO;

    @Column(name = "rPrice")
    private BigDecimal rPrice;
    @Column(name = "rDesPrice")
    private BigDecimal rDesPrice;

/*-------------------------------聯合映射用的的getter、setter--------------------------------------*/

    public RentalOrderVO getRentalOrderVoOrm() {
        return rentalOrderVoOrm;
    }

    public void setRentalOrderVoOrm(RentalOrderVO rentalOrderVoOrm) {
        this.rentalOrderVoOrm = rentalOrderVoOrm;
    }

    public RentalVO getRentalVO() {
        return rentalVO;
    }

    public void setRentalVO(RentalVO rentalVO) {
        this.rentalVO = rentalVO;
    }
/*-------------------------------getter、setter--------------------------------------*/
    public BigDecimal getrPrice() {
        return rPrice;
    }

    public void setrPrice(BigDecimal rPrice) {
        this.rPrice = rPrice;
    }

    public BigDecimal getrDesPrice() {
        return rDesPrice;
    }

    public void setrDesPrice(BigDecimal rDesPrice) {
        this.rDesPrice = rDesPrice;
    }

/*-------------------------------內部類別的 getter、setter--------------------------------------*/

    public CompositeDetail getCompositeDetail() {
        return new CompositeDetail(rentalOrderVoOrm, rentalVO);
    }

    public void setCompositeDetail(CompositeDetail key) {
        key.setRentalOrderVoOrm(this.rentalOrderVoOrm);
        key.setRentalVO(this.rentalVO);
    }

/*-------------------------------因為複合主鍵所以加上的內部類別--------------------------------------*/

    static class CompositeDetail implements Serializable {

        private RentalOrderVO rentalOrderVoOrm;
        private RentalVO rentalVO;

        public CompositeDetail() {

        }

        public CompositeDetail(RentalOrderVO rentalOrderVoOrm, RentalVO rentalVO) {
            this.rentalOrderVoOrm = rentalOrderVoOrm;
            this.rentalVO = rentalVO;
        }

        public RentalOrderVO getRentalOrderVoOrm() {
            return rentalOrderVoOrm;
        }

        public void setRentalOrderVoOrm(RentalOrderVO rentalOrderVoOrm) {
            this.rentalOrderVoOrm = rentalOrderVoOrm;
        }

        public RentalVO getRentalVO() {
            return rentalVO;
        }

        public void setRentalVO(RentalVO rentalVO) {
            this.rentalVO = rentalVO;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeDetail that)) return false;
            return Objects.equals(getRentalOrderVoOrm(), that.getRentalOrderVoOrm()) && Objects.equals(getRentalVO(), that.getRentalVO());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRentalOrderVoOrm(), getRentalVO());
        }

    } // 內部類別結束

    @Override
    public String toString() {
        return "RentalOrderDetails_ORM{" +
                "rentalOrderVoOrm=" + rentalOrderVoOrm +
                ", rental=" + rentalVO +
                ", rPrice=" + rPrice +
                ", rDesPrice=" + rDesPrice +
                '}';
    }

}


