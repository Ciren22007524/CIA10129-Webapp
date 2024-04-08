package com.productcategory.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public interface ProductCategoryService_interface {

	public List<ProductCategoryVO> getAll();

	public ProductCategoryVO getOneProductCatagory(Integer pCatNo);

	public Set<ProductVO> getProductsBypCatNo(Integer pCatNo);
	
	public void deleteProductCategory(Integer pCatNo);
	
}
