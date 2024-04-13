package com.ren.administrator.service;

import com.ren.administrator.dao.AdministratorDAO_interface;
import com.ren.administrator.dao.AdministratorJDBCDAOImpl;
import com.ren.administrator.model.AdministratorVO;

import java.sql.Date;
import java.util.List;

public class AdministratorServiceImpl implements AdministratorService_interface {

    private AdministratorDAO_interface dao;

    // DI
    public AdministratorServiceImpl() {
        dao = new AdministratorJDBCDAOImpl();
    }

    @Override
    public AdministratorVO addAdministrator(String admPwd, String admName, Byte admStat, String admEmail, Integer titleNo, Date admHireDate, byte[] admPhoto) {
        AdministratorVO administratorVO = new AdministratorVO();
        // 將傳入參數放入VO
        administratorVO.setAdmPwd(admPwd);
        administratorVO.setAdmName(admName);
        administratorVO.setAdmStat(admStat);
        administratorVO.setAdmEmail(admEmail);
        administratorVO.setTitleNo(titleNo);
        administratorVO.setAdmHireDate(admHireDate);
        administratorVO.setAdmPhoto(admPhoto);
        // 將VO放入dao定義的方法內，使其執行資料庫操作
        dao.insert(administratorVO);
        // 返回值作為呈現在View上使用
        return administratorVO;
    }

    @Override
    public AdministratorVO getOneAdministrator(Integer admNo) {
        return dao.findByPrimaryKey(admNo);
    }

    @Override
    public List<AdministratorVO> getAll() {
        return dao.getAll();
    }

    @Override
    public AdministratorVO updateAdministrator(Integer admNo, String admPwd, String admName, Byte admStat, String admEmail, Integer titleNo, Date admHireDate, byte[] admPhoto) {
        AdministratorVO administratorVO = new AdministratorVO();
        // 將傳入參數放入VO
        administratorVO.setAdmNo(admNo);
        administratorVO.setAdmPwd(admPwd);
        administratorVO.setAdmName(admName);
        administratorVO.setAdmStat(admStat);
        administratorVO.setAdmEmail(admEmail);
        administratorVO.setTitleNo(titleNo);
        administratorVO.setAdmHireDate(admHireDate);
        // 將VO放入dao定義的方法內，使其執行資料庫操作
        dao.update(administratorVO);
        // 返回值作為呈現在View上使用
        return administratorVO;
    }

    @Override
    public void deleteAdministrator(Integer admNo) {
        dao.delete(admNo);
    }

    @Override
    public void uploadPhoto(byte[] admPhoto) {
        dao.upload(admPhoto);
    }

}

