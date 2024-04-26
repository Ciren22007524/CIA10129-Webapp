package com.ren.listener;

import com.ren.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PreloadResourcesListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Hibernate工廠初始化
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.shutdown();
    }
}
