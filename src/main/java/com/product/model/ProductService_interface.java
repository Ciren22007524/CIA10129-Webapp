package com.product.model;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService_interface {
	
	public ProductVO addProduct(Integer pCatNo, String pName, String pInfo, Integer pSize, String pColor, BigDecimal pPrice,
			Byte pStat, Integer pSalQty, Integer pComPeople, Integer pComScore);

	public ProductVO updateProduct(Integer pNo, Integer pCatNo, String pName, String pInfo, Integer pSize, String pColor, BigDecimal pPrice,
			Byte pStat, Integer pSalQty, Integer pComPeople, Integer pComScore);

	public void deleteProduct(Integer pNo);

	public ProductVO getOneProduct(Integer pNo);

	public List<ProductVO> getAll();

}
