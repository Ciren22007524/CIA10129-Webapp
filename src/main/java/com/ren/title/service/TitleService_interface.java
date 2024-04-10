package com.ren.title.service;

import com.ren.product.model.ProductVO;
import com.ren.title.model.TitleAdmVO;
import com.ren.title.model.TitleVO;

import java.math.BigDecimal;
import java.util.List;

public interface TitleService_interface {
    // 新增(將前端request值放入VO由DAO執行SQL語法，並返回VO由Controller轉給View)
    public TitleVO addProduct(Integer titleNo, String titleName);
    // 查詢單筆資料
    public TitleVO getOneProduct(Integer titleNo);
    public TitleAdmVO getAdms(Integer titleNo);
    public TitleAdmVO getAdms(String titleName);
    // 查詢所有資料
    public List<TitleVO> getAll();
    public List<TitleAdmVO> getAdmsAll();
    // 修改(將前端request值放入VO由DAO執行SQL語法，返回VO由Controller轉給View)
    public TitleVO updateProduct(Integer titleNo, String titleName);
    // 刪除
    public void deleteProduct(Integer titleNo);


}
