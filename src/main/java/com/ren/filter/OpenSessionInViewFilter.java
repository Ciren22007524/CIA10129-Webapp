package com.ren.filter;

import com.ren.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" })
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try {
            System.out.println("filter open transaction");
            factory.getCurrentSession().beginTransaction();
            chain.doFilter(req, res);
            factory.getCurrentSession().getTransaction().commit();
            System.out.println("commit執行");
        } catch (Exception e) {
            if (factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
                System.out.println("rollback執行");
            }
            e.printStackTrace();
            // 不要在發生異常後繼續過濾
            // chain.doFilter(req, res);
        }
    }

}
