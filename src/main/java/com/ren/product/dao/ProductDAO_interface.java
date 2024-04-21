package com.ren.product.dao;

import com.ren.product.model.ProductVO;

import java.util.*;

public interface ProductDAO_interface {

    int insert(ProductVO entity);

    int update(ProductVO entity);

    int delete(Integer id);

    ProductVO getById(Integer id);

    List<ProductVO> getAll();

    List<ProductVO> getByCompositeQuery(Map<String, String> map);

    List<ProductVO> getAll(int currentPage);

    long getTotal();

}

