package com.yu.rental.model;

import com.ren.product.model.ProductVO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

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
    @OneToMany(mappedBy = "RentalPic", cascade = CascadeType.ALL)
    private Set<ProductVO> rentalPictures;
    @OneToMany(mappedBy = "RentalOrderDetails", cascade = CascadeType.ALL)
    private Set<ProductVO> rentalOrderDetals;
    @OneToMany(mappedBy = "ProductPicture", cascade = CascadeType.ALL)
    private Set<ProductVO> productPictures;

}
