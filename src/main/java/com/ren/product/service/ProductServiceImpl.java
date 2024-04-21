package com.ren.product.service;

import com.ren.product.dao.ProductHibernateDAOImpl;
import com.ren.product.dao.ProductJNDIDAOImpl;
import com.ren.product.model.ProductVO;
import com.ren.product.dao.ProductDAO_interface;
import com.ren.product.dao.ProductJDBCDAOImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService_interface {

    private ProductDAO_interface dao;

    // DI
    public ProductServiceImpl() {
        dao = new ProductHibernateDAOImpl();
    }

    @Override
    public ProductVO addProduct(ProductVO productVO) {
        return null;
    }

    @Override
    public ProductVO getOneProduct(Integer pNo) {
        return null;
    }

    @Override
    public List<ProductVO> getAll(int currentPage) {
        return null;
    }

    @Override
    public List<ProductVO> getProductsByCompositeQuery(Map<String, String[]> map) {
        return null;
    }

    @Override
    public ProductVO updateProduct(ProductVO productVO) {
        return null;
    }

    @Override
    public void deleteProduct(Integer pNo) {

    }
}
