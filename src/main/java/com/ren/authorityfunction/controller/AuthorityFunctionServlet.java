package com.ren.authorityfunction.controller;

import com.ren.authorityfunction.model.AuthorityFunctionVO;
import com.ren.authorityfunction.service.AuthorityFunctionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/authorityfunction/authorityFunction.do")
public class AuthorityFunctionServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("authorityFunctionNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入職位編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/authorityFunction/select_authorityFunction.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer authorityFunctionNo = null;
            try {
                authorityFunctionNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/authorityFunction/select_authorityFunction.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            AuthorityFunctionServiceImpl authorityFunctionSvc = new AuthorityFunctionServiceImpl();
            AuthorityFunctionVO authorityFunctionVO = authorityFunctionSvc.getOneAuthorityFunction(authorityFunctionNo);
            // 引用類型的屬性在未附值時預設為null
            if (authorityFunctionVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/authorityFunction/select_authorityFunction.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("authorityFunctionVO", authorityFunctionVO);
            String url = "/authorityFunction/listOneAuthorityFunction.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer authorityFunctionNo = Integer.valueOf(req.getParameter("authorityFunctionNo"));

            /*************************** 2.開始查詢資料 ****************************************/
            AuthorityFunctionServiceImpl authorityFunctionSvc = new AuthorityFunctionServiceImpl();
            AuthorityFunctionVO authorityFunctionVO = authorityFunctionSvc.getOneAuthorityFunction(authorityFunctionNo);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("authorityFunctionVO", authorityFunctionVO);
            String url = "/authorityFunction/update_authorityFunction_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer authorityFunctionNo = Integer.valueOf(req.getParameter("authorityFunctionNo").trim());

            String authorityFunctionName = req.getParameter("authorityFunctionName");
            String authorityFunctionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (authorityFunctionName == null || authorityFunctionName.trim().length() == 0) {
                errorMsgs.add("職位名稱: 請勿空白");
            } else if (!authorityFunctionName.trim().matches(authorityFunctionNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("職位名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            AuthorityFunctionVO authorityFunctionVO = new AuthorityFunctionVO();
            authorityFunctionVO.setTitleNo(authorityFunctionNo);
            authorityFunctionVO.setTitleName(authorityFunctionName);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("authorityFunctionVO", authorityFunctionVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/authorityFunction/update_authorityFunction_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            AuthorityFunctionServiceImpl authorityFunctionSvc = new AuthorityFunctionServiceImpl();
            // 執行update後返回以authorityFunctionNo查詢更新後的VO
            authorityFunctionVO = authorityFunctionSvc.updateAuthorityFunction(authorityFunctionNo, authorityFunctionName);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("authorityFunctionVO", authorityFunctionVO);
            String url = "/authorityFunction/listOneTitle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String authorityFunctionName = req.getParameter("authorityFunctionName");
            String authorityFunctionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (authorityFunctionName == null || authorityFunctionName.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!authorityFunctionName.trim().matches(authorityFunctionNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            AuthorityFunctionVO authorityFunctionVO = new AuthorityFunctionVO();
            authorityFunctionVO.setTitleName(authorityFunctionName);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("authorityFunctionVO", authorityFunctionVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/authorityFunction/addAuthorityFunction.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            AuthorityFunctionServiceImpl authorityFunctionSvc = new AuthorityFunctionServiceImpl();
            authorityFunctionSvc.addAuthorityFunction(authorityFunctionName);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/authorityFunction/listAllAuthorityFunction.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("delete".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer authorityFunctionNo = Integer.valueOf(req.getParameter("authorityFunctionNo"));

            /*************************** 2.開始刪除資料 ***************************************/
            AuthorityFunctionServiceImpl authorityFunctionSvc = new AuthorityFunctionServiceImpl();
            authorityFunctionSvc.deleteAuthorityFunction(authorityFunctionNo);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/authorityFunction/listAllAuthorityFunction.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }

}
