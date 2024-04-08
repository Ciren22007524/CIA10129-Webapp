package com.product.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService_interface {
	
	private ProductDAO_interface dao;

	public ProductServiceImpl() {
		dao = new ProductJDBCDAOImpl();
	}
	
	@Override
	public ProductVO addProduct(Integer pCatNo, String pName, String pInfo, Integer pSize, String pColor, BigDecimal pPrice,
			Byte pStat, Integer pSalQty, Integer pComPeople, Integer pComScore) {
		// TODO Auto-generated method stub
		ProductVO productVO = new ProductVO();

		productVO.setpCatNo(pCatNo);
		productVO.setpName(pName);
		productVO.setpInfo(pInfo);
		productVO.setpSize(pSize);
		productVO.setpColor(pColor);
		productVO.setpPrice(pPrice);
		productVO.setpStat(pStat);
		productVO.setpSalQty(pSalQty);
		productVO.setpComPeople(pComPeople);
		productVO.setpComScore(pComScore);
		dao.insert(productVO);

		return productVO;
	}

	@Override
	public ProductVO updateProduct(Integer pNo, Integer pCatNo, String pName, String pInfo, Integer pSize, String pColor,
			BigDecimal pPrice, Byte pStat, Integer pSalQty, Integer pComPeople, Integer pComScore) {
		// TODO Auto-generated method stub
		
		ProductVO productVO = new ProductVO();
		
		productVO.setpNo(pNo);
		productVO.setpCatNo(pCatNo);
		productVO.setpName(pName);
		productVO.setpInfo(pInfo);
		productVO.setpSize(pSize);
		productVO.setpColor(pColor);
		productVO.setpPrice(pPrice);
		productVO.setpStat(pStat);
		productVO.setpSalQty(pSalQty);
		productVO.setpComPeople(pComPeople);
		productVO.setpComScore(pComScore);
		dao.update(productVO);

		return productVO;
	}

	@Override
	public void deleteProduct(Integer pNo) {
		// TODO Auto-generated method stub
		dao.delete(pNo);
	}

	@Override
	public ProductVO getOneProduct(Integer pNo) {
		// TODO Auto-generated method stub
		return dao.findByPrimaryKey(pNo);
	}

	@Override
	public List<ProductVO> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
