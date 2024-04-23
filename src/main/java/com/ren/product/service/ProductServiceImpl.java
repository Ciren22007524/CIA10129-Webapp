package com.ren.product.service;

import com.ren.product.dao.ProductHibernateDAOImpl;
import com.ren.product.model.ProductVO;
import com.ren.product.dao.ProductDAO_interface;
import com.ren.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductServiceImpl implements ProductService_interface {
    private final int SUCCESS = 1;
    private final int FAILURE = -1;

    private ProductDAO_interface dao;

    // DI
    public ProductServiceImpl() {
        dao = new ProductHibernateDAOImpl();
    }

    @Override
    public ProductVO addProduct(ProductVO productVO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ProductVO addedProduct = dao.getById(dao.insert(productVO));
            session.getTransaction().commit();
            return addedProduct;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public ProductVO getOneProduct(Integer pNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ProductVO productVO = dao.getById(pNo);
            session.getTransaction().commit();
            return productVO;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<ProductVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ProductVO> list = dao.getAll();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductVO> getAll(int currentPage) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ProductVO> list = dao.getAll(currentPage);
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductVO> getProductsByCompositeQuery(Map<String, String[]> map) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Map<String, String> query = new HashMap<>();
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();
            // 因為請求參數裡包含了action，做個去除動作
            if ("action".equals(key)) {
                continue;
            }
            // 若是value為空即代表沒有查詢條件，做個去除動作
            String value = row.getValue()[0];
            if (value.isEmpty() || value == null) {
                continue;
            }
            query.put(key, value);
        }

        try {
            session.beginTransaction();
            List<ProductVO> list = dao.getByCompositeQuery(query);
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductVO updateProduct(ProductVO productVO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            int result = dao.update(productVO);
            if (result == SUCCESS) {
                System.out.println("修改成功");
            } else if (result == FAILURE) {
                System.out.println("修改失敗");
            }
            ProductVO updatedProduct = dao.getById(productVO.getpNo());
            session.getTransaction().commit();
            return updatedProduct;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteProduct(Integer pNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            int result = dao.delete(pNo);
            if (result == SUCCESS) {
                System.out.println("刪除成功");
            } else if (result == FAILURE) {
                System.out.println("刪除失敗");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
