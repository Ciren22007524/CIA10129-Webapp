package com.ren.administrator.controller;

import com.ren.administrator.model.AdministratorVO;
import com.ren.administrator.service.AdministratorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@MultipartConfig
@WebServlet("/administrator/administrator.do")
public class AdministratorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("admNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入員工編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/select_administrator.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer admNo = null;
            try {
                admNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/select_administrator.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            // 執行Service的getOnProduct，該方法執行DAO的findByPrimaryKey，將資料庫內的資料以VO的形式傳回
            AdministratorVO adminitratorVO = administratorSvc.getOneAdministrator(admNo);
            // 引用類型的屬性在未附值時預設為null
            if (adminitratorVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/select_administrator.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("adminitratorVO", adminitratorVO); // 資料庫取出的adminitratorVO物件,存入req
            String url = "/administrator/listOneAdministrator.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer admNo = Integer.valueOf(req.getParameter("admNo"));

            /*************************** 2.開始查詢資料 ****************************************/
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            AdministratorVO adminitratorVO = administratorSvc.getOneAdministrator(admNo);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("adminitratorVO", adminitratorVO); // 資料庫取出的adminitratorVO物件,存入req
            String url = "/administrator/update_administrator_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_administrator_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_administrator_input.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer admNo = Integer.valueOf(req.getParameter("admNo").trim());

            String admPwd = req.getParameter("admPwd");
            String admPwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (admPwd == null || admPwd.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!admPwd.trim().matches(admPwdReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String admName = req.getParameter("admName").trim();
            if (admName == null || admName.trim().length() == 0) {
                errorMsgs.add("商品資訊請勿空白");
            }

            Byte admStat = null;
            try {
                admStat = Byte.valueOf(req.getParameter("admStat").trim());
            } catch (NumberFormatException e) {
                admStat = Byte.valueOf("0");
                errorMsgs.add("管理員狀態請填數字.");
            }

            String admEmail = req.getParameter("admEmail").trim();
            if (admEmail == null || admEmail.trim().length() == 0) {
                errorMsgs.add("email請勿空白");
            }

            Integer titleNo = Integer.valueOf(req.getParameter("titleNo").trim());

            java.sql.Date admHireDate = null;
            try {
                admHireDate = java.sql.Date.valueOf(req.getParameter("admHireDate").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.add("請輸入日期");
            }

            byte[] admPhoto = null;
            Part photoPart = req.getPart("admPhoto");
            if (photoPart != null) {
                try (InputStream inputStream = photoPart.getInputStream()) {
                    admPhoto = inputStream.readAllBytes();
                } catch (IOException e) {
                    errorMsgs.add("讀取 admPhoto 時發生錯誤: " + e.getMessage());
                }
            } else {
                errorMsgs.add("未找到照片");
            }

            AdministratorVO adminitratorVO = new AdministratorVO();
            adminitratorVO.setAdmNo(admNo);
            adminitratorVO.setAdmPwd(admPwd);
            adminitratorVO.setAdmName(admName);
            adminitratorVO.setAdmStat(admStat);
            adminitratorVO.setAdmEmail(admEmail);
            adminitratorVO.setTitleNo(titleNo);
            adminitratorVO.setAdmHireDate(admHireDate);
            adminitratorVO.setAdmPhoto(admPhoto);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("adminitratorVO", adminitratorVO); // 含有輸入格式錯誤的adminitratorVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/update_administrator_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            adminitratorVO = administratorSvc.updateAdministrator(admNo, admPwd, admName, admStat, admEmail, titleNo, admHireDate, admPhoto);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("adminitratorVO", adminitratorVO); // 資料庫update成功後,正確的的adminitratorVO物件,存入req
            String url = "/administrator/listOneAdministrator.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
            successView.forward(req, res);
        }

        if ("upload".equals(action)) { // 來自addAdministrator.jsp的請求
            System.out.println("幹林良1");
            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            System.out.println("幹林良2");
            byte[] admPhoto = null;
            try {
                System.out.println("幹林良3");
                Part photoPart = req.getPart("admPhoto");
                if (photoPart != null) {
                    System.out.println("幹林良4");
                    InputStream inputStream = photoPart.getInputStream();
                    System.out.println("幹林良5");
                    admPhoto = inputStream.readAllBytes();
                    System.out.println("幹林良6");
                } else {
                    errorMsgs.add("未找到 照片 部分。");
                }
            } catch (IOException e) {
                errorMsgs.add("讀取 admPhoto 時發生錯誤: " + e.getMessage());
            }
            System.out.println("幹林良7");

//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                req.setAttribute("adminitratorVO", adminitratorVO); // 含有輸入格式錯誤的adminitratorVO物件,也存入req
//                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/addAdministrator.jsp");
//                failureView.forward(req, res);
//                return;
//            }

            /*************************** 2.開始新增資料 ***************************************/
            System.out.println("幹林良8");
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            System.out.println("幹林良9");
            administratorSvc.uploadPhoto(admPhoto);
            System.out.println("幹林良10");

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//            String url = "/administrator/listAllAdministrator.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
//            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addAdministrator.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

            String admPwd = req.getParameter("admPwd");
            String admPwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (admPwd == null || admPwd.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!admPwd.trim().matches(admPwdReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String admName = req.getParameter("admName").trim();
            if (admName == null || admName.trim().length() == 0) {
                errorMsgs.add("商品資訊請勿空白");
            }

            Byte admStat = null;
            try {
                admStat = Byte.valueOf(req.getParameter("admStat").trim());
            } catch (NumberFormatException e) {
                admStat = Byte.valueOf("0");
                errorMsgs.add("管理員狀態請填數字.");
            }

            String admEmail = req.getParameter("admEmail").trim();
            if (admEmail == null || admEmail.trim().length() == 0) {
                errorMsgs.add("email請勿空白");
            }

            Integer titleNo = Integer.valueOf(req.getParameter("titleNo").trim());

            java.sql.Date admHireDate = null;
            try {
                admHireDate = java.sql.Date.valueOf(req.getParameter("admHireDate").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.add("請輸入日期");
            }

            byte[] admPhoto = null;
            try {
                Part photoPart = req.getPart("admPhoto");
                if (photoPart != null) {
                    InputStream inputStream = photoPart.getInputStream();
                    admPhoto = inputStream.readAllBytes();
                } else {
                    errorMsgs.add("未找到 照片 部分。");
                }
            } catch (IOException e) {
                errorMsgs.add("讀取 admPhoto 時發生錯誤: " + e.getMessage());
            }

            AdministratorVO adminitratorVO = new AdministratorVO();
            adminitratorVO.setAdmPwd(admPwd);
            adminitratorVO.setAdmName(admName);
            adminitratorVO.setAdmStat(admStat);
            adminitratorVO.setAdmEmail(admEmail);
            adminitratorVO.setTitleNo(titleNo);
            adminitratorVO.setAdmHireDate(admHireDate);
            adminitratorVO.setAdmPhoto(admPhoto);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("adminitratorVO", adminitratorVO); // 含有輸入格式錯誤的adminitratorVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/administrator/addAdministrator.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            adminitratorVO = administratorSvc.addAdministrator(admPwd, admName, admStat, admEmail, titleNo, admHireDate, admPhoto);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/administrator/listAllAdministrator.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // 來自listAllProduct.jsp

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer admNo = Integer.valueOf(req.getParameter("admNo"));

            /*************************** 2.開始刪除資料 ***************************************/
            AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
            administratorSvc.deleteAdministrator(admNo);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/administrator/listAllAdministrator.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }



    }
}
