package com.howard.rentalmytrack.model;

import com.roger.member.model.MemberVO;
import com.yu.rental.model.RentalVO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "rentalmytrack")
@IdClass(RentalMyTrackVO.CompositeTrack.class)
public class RentalMyTrackVO implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "rNo", referencedColumnName = "rNo")
    private RentalVO rentalVO;
    @Id
    @ManyToOne
    @JoinColumn(name = "memNo", referencedColumnName = "memNo")
    private  MemberVO memberVO;
	@Column(name = "rTrackTime")
    private Timestamp rTrackTime;
	@Column(name = "expRentalDate")
    private Date expRentalDate;

    public RentalVO getRentalVO() {
        return rentalVO;
    }

    public void setRentalVO(RentalVO rentalVO) {
        this.rentalVO = rentalVO;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    public Timestamp getrTrackTime() {
        return rTrackTime;
    }

    public void setrTrackTime(Timestamp rTrackTime) {
        this.rTrackTime = rTrackTime;
    }

    public Date getExpRentalDate() {
        return expRentalDate;
    }

    public void setExpRentalDate(Date expRentalDate) {
        this.expRentalDate = expRentalDate;
    }

    public void setCompositeTrack(CompositeTrack key) {
        key.setRentalVO(this.rentalVO);
        key.setMemberVO(this.memberVO);
    }

    public CompositeTrack getCompositeTrack() {
        return new CompositeTrack(this.rentalVO, this.memberVO);
    }

    static class CompositeTrack implements Serializable {

        private RentalVO rentalVO;
        private MemberVO memberVO;

        public CompositeTrack() {

        }

        public CompositeTrack(RentalVO rentalVO, MemberVO memberVO) {
            this.rentalVO = rentalVO;
            this.memberVO = memberVO;
        }

        public RentalVO getRentalVO() {
            return rentalVO;
        }

        public void setRentalVO(RentalVO rentalVO) {
            this.rentalVO = rentalVO;
        }

        public MemberVO getMemberVO() {
            return memberVO;
        }

        public void setMemberVO(MemberVO memberVO) {
            this.memberVO = memberVO;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeTrack that)) return false;
            return Objects.equals(getRentalVO(), that.getRentalVO()) && Objects.equals(getMemberVO(), that.getMemberVO());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRentalVO(), getMemberVO());
        }

    }

    @Override
    public String toString() {
        return "RentalMyTrackVO{" +
                "rentalVO=" + rentalVO +
                ", memberVO=" + memberVO +
                ", rTrackTime=" + rTrackTime +
                ", expRentalDate=" + expRentalDate +
                '}';
    }

    public RentalMyTrackVO() {

    }

}
