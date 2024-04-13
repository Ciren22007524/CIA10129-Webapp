package com.ren.title.controller;

import com.ren.product.model.ProductVO;
import com.ren.product.service.ProductServiceImpl;
import com.ren.title.model.TitleVO;
import com.ren.title.service.TitleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/title/title.do")
public class TitleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("titleNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/title/select_title.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer titleNo = null;
			try {
				titleNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/title/select_title.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			TitleServiceImpl titleSvc = new TitleServiceImpl();
			// 執行Service的getOnProduct，該方法執行DAO的findByPrimaryKey，將資料庫內的資料以VO的形式傳回
			TitleVO titleVO = titleSvc.getOneTitle(titleNo);
			// 引用類型的屬性在未附值時預設為null
			if (titleVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/title/select_title.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("titleVO", titleVO); // 資料庫取出的titleVO物件,存入req
			String url = "/title/listOneTitle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTitle.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllTitle.jsp的請求

			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer titleNo = Integer.valueOf(req.getParameter("titleNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			TitleServiceImpl titleSvc = new TitleServiceImpl();
			TitleVO titleVO = titleSvc.getOneTitle(titleNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("titleVO", titleVO); // 資料庫取出的titleVO物件,存入req
			String url = "/title/update_title_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_title_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_title_input.jsp的請求

			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer titleNo = Integer.valueOf(req.getParameter("titleNo").trim());

			String titleName = req.getParameter("titleName");
			String titleNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (titleName == null || titleName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!titleName.trim().matches(titleNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			TitleVO titleVO = new TitleVO();
			titleVO.setTitleNo(titleNo);
			titleVO.setTitleName(titleName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("titleVO", titleVO); // 含有輸入格式錯誤的titleVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/title/update_title_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			TitleServiceImpl titleSvc = new TitleServiceImpl();
			titleVO = titleSvc.updateTitle(titleNo, titleName);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("titleVO", titleVO); // 資料庫update成功後,正確的的titleVO物件,存入req
			String url = "/title/listOneTitle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTitle.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addTitle.jsp的請求

			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String titleName = req.getParameter("titleName");
			String titleNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (titleName == null || titleName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!titleName.trim().matches(titleNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			TitleVO titleVO = new TitleVO();
			titleVO.setTitleName(titleName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("titleVO", titleVO); // 含有輸入格式錯誤的titleVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/title/addTitle.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			TitleServiceImpl titleSvc = new TitleServiceImpl();
			titleVO = titleSvc.addTitle(titleName);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/title/listAllTitle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTitle.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllTitle.jsp

			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer titleNo = Integer.valueOf(req.getParameter("titleNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			TitleServiceImpl titleSvc = new TitleServiceImpl();
			titleSvc.deleteTitle(titleNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/title/listAllTitle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}