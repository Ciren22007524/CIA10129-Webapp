package com.ren.administrator.dao;

import com.ren.administrator.model.AdministratorVO;
import com.ren.product.model.ProductVO;

import java.util.List;

public interface AdministratorDAO_interface {
    // 新增
    public void insert(AdministratorVO administratorVO);
    // 查詢單項
    public AdministratorVO findByPrimaryKey(Integer admNo);
    // 查詢所有
    public List<AdministratorVO> getAll();
    // 修改
    public void update(AdministratorVO administratorVO);
    // 刪除
    public void delete(Integer admNo);

    public void upload(byte[] admPhoto);

}
