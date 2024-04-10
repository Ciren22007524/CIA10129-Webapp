package com.ren.productcategory.service;

import java.util.List;
import java.util.Set;

import com.ren.product.model.ProductVO;
import com.ren.productcategory.model.ProductCategoryVO;
import com.ren.productcategory.dao.ProductCategoryDAO_interface;
import com.ren.productcategory.dao.ProductCategoryJDBCDAOImpl;

public class ProductCategoryServiceImpl implements ProductCategoryService_interface {
	
	private ProductCategoryDAO_interface dao;

	public ProductCategoryServiceImpl() {
		dao = new ProductCategoryJDBCDAOImpl();
	}
	
	@Override
	public List<ProductCategoryVO> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public ProductCategoryVO getOneProductCatagory(Integer pCatNo) {
		// TODO Auto-generated method stub
		return dao.findByPrimaryKey(pCatNo);
	}

	@Override
	public Set<ProductVO> getProductsBypCatNo(Integer pCatNo) {
		// TODO Auto-generated method stub
		return dao.getProductsBypCatNo(pCatNo);
	}

	@Override
	public void deleteProductCategory(Integer pCatNo) {
		// TODO Auto-generated method stub
		dao.delete(pCatNo);
	}

}
