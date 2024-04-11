package com.ren.administrator.service;

import com.ren.administrator.model.AdministratorVO;

import java.sql.Date;
import java.util.List;

public interface AdministratorService_interface {
    // 新增(將前端request值放入VO由DAO執行SQL語法，並返回VO由Controller轉給View)
    public AdministratorVO addAdministratorVO(String admPwd, String admName, Byte admStat, String admEmail, Integer titleNo, Date admHireDate);
    // 查詢單筆資料
    public AdministratorVO getOneAdministratorVO(Integer admNo);
    // 查詢所有資料
    public List<AdministratorVO> getAll();
    // 修改(返回值由Controller轉給View)
    public AdministratorVO updateAdministratorVO(Integer admNo, String admPwd, String admName, Byte admStat, String admEmail, Integer titleNo, Date admHireDate);
    // 刪除
    public void deleteAdministratorVO(Integer admNo);

}
