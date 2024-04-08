package com.productcategory.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public interface ProductCategoryDAO_interface {
	
    public void insert(ProductCategoryVO productCategoryVO);
    public void update(ProductCategoryVO productCategoryVO);
    public void delete(Integer pCatNo);
    public ProductCategoryVO findByPrimaryKey(Integer pCatNo);
    public List<ProductCategoryVO> getAll();
    //查詢某類別的衣服(一對多)(回傳 Set)
    public Set<ProductVO> getProductsBypCatNo(Integer pCatNo);

}
