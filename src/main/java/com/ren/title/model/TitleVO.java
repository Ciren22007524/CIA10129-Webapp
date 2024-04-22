package com.ren.title.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Title")
public class TitleVO {
    @Id
    @Column(name = "titleNo")
    private Integer titleNo;
    @Column(name = "titleName")
    private String titleName;
    @OneToMany(mappedBy = "AdmAuthority", cascade = CascadeType.ALL)
    private Set<TitleVO> admAuthoritys;
    @OneToMany(mappedBy = "Administrator", cascade = CascadeType.ALL)
    private Set<TitleVO> administrators;

    public TitleVO() {

    }

    public TitleVO(Integer titleNo, String titleName, Set<TitleVO> admAuthoritys, Set<TitleVO> administrators) {
        this.titleNo = titleNo;
        this.titleName = titleName;
        this.admAuthoritys = admAuthoritys;
        this.administrators = administrators;
    }

    public Integer getTitleNo() {
        return titleNo;
    }

    public void setTitleNo(Integer titleNo) {
        this.titleNo = titleNo;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Set<TitleVO> getAdmAuthoritys() {
        return admAuthoritys;
    }

    public void setAdmAuthoritys(Set<TitleVO> admAuthoritys) {
        this.admAuthoritys = admAuthoritys;
    }

    public Set<TitleVO> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Set<TitleVO> administrators) {
        this.administrators = administrators;
    }
}
