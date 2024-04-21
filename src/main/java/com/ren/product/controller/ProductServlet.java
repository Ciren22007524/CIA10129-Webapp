package com.ren.product.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ren.product.model.ProductVO;
import com.ren.product.service.ProductServiceImpl;
import com.ren.product.service.ProductService_interface;

@WebServlet("/product/product.do")
public class ProductServlet extends HttpServlet {
    /*****************************常數區***************************************/
    // 請輸入表格名稱:
    private final String TABLE_NAME = "";
    // 網址
    private final String SELECT = "/product/select_product.jsp";
    private final String LIST_ONE = "/product/listOneProduct.jsp";
    private final String LIST_ALL = "/product/listAllProduct.jsp";
    private final String ADD = "/product/addProduct.jsp";
    private final String UPDATE = "/product/update_product_input.jsp";

    private ProductService_interface productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String forwardPath = "";
        switch (action) {
            case "insert":
                forwardPath =
                break;
            case "getOne_For_Display":

                break;
            case "getOne_For_Update":

                break;
            case "getAll":

                break;
            case "compositeQuery":

                break;
            case "update":

                break;
            case "delete":

                break;
            case "getProdcutDetails":

                break;
            default:
                forwardPath = SELECT;
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);

    }

    private String getOne(HttpServletRequest req, HttpServletResponse res) {

        List<String> errorMsgs = new LinkedList<>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
        String str = req.getParameter("pNo");
        if (validateSpace(str)) {
            // 因為使用兩次 + 會 new 兩次 StringBuilder物件，這裡直接新增一個StringBuilder物件
            // 請輸入 表格名稱 編號
            errorMsgs.add(new StringBuilder("請輸入")
                    .append(TABLE_NAME)
                    .append("編號")
                    .toString());
        }
        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            return SELECT;// 回導覽
        }

        Integer pNo = null;
        try {
            pNo = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("員工編號格式不正確");
        }
        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req.getRequestDispatcher("/product/select_product.jsp");
            failureView.forward(req, res);
            return;// 程式中斷
        }

        /*************************** 2.開始查詢資料 *****************************************/
        ProductServiceImpl productSvc = new ProductServiceImpl();
        // 執行Service的getOnProduct，該方法執行DAO的findByPrimaryKey，將資料庫內的資料以VO的形式傳回
        ProductVO productVO = productSvc.getOneProduct(pNo);
        // 引用類型的屬性在未附值時預設為null
        if (productVO == null) {
            errorMsgs.add("查無資料");
        }
        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req.getRequestDispatcher("/product/select_product.jsp");
            failureView.forward(req, res);
            return;// 程式中斷
        }

        /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
        req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
        String url = "/product/listOneProduct.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
        successView.forward(req, res);

    }

    private String getOneForUpdate(HttpServletRequest req, HttpServletResponse res) {
        // 作轉傳資料使用

        // 當點擊修改時，透過Service呼叫dao的查詢單項方法來獲得該資料的VO，傳到修改頁面
        List<String> errorMsgs = new LinkedList<>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        /*************************** 1.接收請求參數 ****************************************/
        Integer pNo = Integer.valueOf(req.getParameter("pNo"));

        /*************************** 2.開始查詢資料 ****************************************/
        ProductServiceImpl productSvc = new ProductServiceImpl();
        ProductVO productVO = productSvc.getOneProduct(pNo);

        /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
        req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
        String url = "/product/update_product_input.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_input.jsp
        successView.forward(req, res);

        return;
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new LinkedList<>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
        Integer pNo = Integer.valueOf(req.getParameter("pNo").trim());

        String pName = req.getParameter("pName");
        String pNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (pName == null || pName.trim().length() == 0) {
            errorMsgs.add("商品名稱: 請勿空白");
        } else if (!pName.trim().matches(pNameReg)) { // 以下練習正則(規)表示式(regular-expression)
            errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        String pInfo = req.getParameter("pInfo").trim();
        if (pInfo == null || pInfo.trim().length() == 0) {
            errorMsgs.add("商品資訊請勿空白");
        }

        Integer pSize = Integer.valueOf(req.getParameter("pSize").trim());

        String pColor = req.getParameter("pColor").trim();
        if (pColor == null || pColor.trim().length() == 0) {
            errorMsgs.add("顏色請勿空白");
        }

        BigDecimal pPrice = null;
        try {
            String price = req.getParameter("pPrice").trim();
            pPrice = new BigDecimal(price);
        } catch (NumberFormatException e) {
            pPrice = BigDecimal.ZERO;
            errorMsgs.add("薪水請填數字.");
        }

        Byte pStat = null;
        try {
            pStat = Byte.valueOf(req.getParameter("pStat").trim());
        } catch (NumberFormatException e) {
            pStat = Byte.valueOf("0");
            errorMsgs.add("商品狀態請填數字.");
        }

        Integer pSalQty = Integer.valueOf(req.getParameter("pSalQty").trim());

        Integer pComPeople = Integer.valueOf(req.getParameter("pComPeople").trim());

        Integer pComScore = Integer.valueOf(req.getParameter("pComScore").trim());

        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
            failureView.forward(req, res);
            return; // 程式中斷
        }

        /*************************** 2.開始修改資料 *****************************************/
        ProductServiceImpl productSvc = new ProductServiceImpl();
        ProductVO productVO = productSvc.updateProduct(pNo, pName, pInfo, pSize, pColor, pPrice, pStat, pSalQty,
                pComPeople, pComScore);

        /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
        req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的productVO物件,存入req
        String url = "/product/listOneProduct.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
        successView.forward(req, res);

        return;
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) {

        List<String> errorMsgs = new LinkedList<>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
        Integer pCatNo = Integer.valueOf(req.getParameter("pCatNo").trim());

        String pName = req.getParameter("pName");
        String pNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (pName == null || pName.trim().length() == 0) {
            errorMsgs.add("商品名稱: 請勿空白");
        } else if (!pName.trim().matches(pNameReg)) { // 以下練習正則(規)表示式(regular-expression)
            errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        String pInfo = req.getParameter("pInfo").trim();
        if (pInfo == null || pInfo.trim().length() == 0) {
            errorMsgs.add("商品資訊請勿空白");
        }

        Integer pSize = Integer.valueOf(req.getParameter("pSize").trim());

        String pColor = req.getParameter("pColor").trim();
        if (pColor == null || pColor.trim().length() == 0) {
            errorMsgs.add("顏色請勿空白");
        }

        BigDecimal pPrice = null;
        try {
            String price = req.getParameter("pPrice").trim();
            pPrice = new BigDecimal(price);
        } catch (NumberFormatException e) {
            pPrice = BigDecimal.ZERO;
            errorMsgs.add("薪水請填數字.");
        }

        Byte pStat = null;
        try {
            pStat = Byte.valueOf(req.getParameter("pStat").trim());
        } catch (NumberFormatException e) {
            pStat = Byte.valueOf("0");
            errorMsgs.add("商品狀態請填數字.");
        }

        Integer pSalQty = Integer.valueOf(req.getParameter("pSalQty").trim());

        Integer pComPeople = Integer.valueOf(req.getParameter("pComPeople").trim());

        Integer pComScore = Integer.valueOf(req.getParameter("pComScore").trim());

        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
            failureView.forward(req, res);
            return;
        }

        /*************************** 2.開始新增資料 ***************************************/
        ProductServiceImpl productSvc = new ProductServiceImpl();
        productSvc.addProduct(pCatNo, pName, pInfo, pSize, pColor, pPrice, pStat, pSalQty, pComPeople, pComScore);

        /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
        String url = "/product/listAllProduct.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
        successView.forward(req, res);

        return;
    }

    private String delete() {
        List<String> errorMsgs = new LinkedList<>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);
        System.out.println("沒事");
        /*************************** 1.接收請求參數 ***************************************/
        Integer pNo = Integer.valueOf(req.getParameter("pNo"));

        /*************************** 2.開始刪除資料 ***************************************/
        ProductServiceImpl productSvc = new ProductServiceImpl();
        productSvc.deleteProduct(pNo);

        /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
        String url = "/product/listAllProduct.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
        successView.forward(req, res);

        return;
    }

    private String getProductDetails() {
        Integer pNo = Integer.valueOf(req.getParameter("pNo").trim());

        // 根据商品编号获取商品的详细信息
        ProductServiceImpl productSvc = new ProductServiceImpl();
        ProductVO productVO = productSvc.getOneProduct(pNo);

        if (productVO != null) {
            // 将商品详细信息转换为 JSON 格式
            Gson gson = new Gson();
            String json = gson.toJson(productVO);

            // 设置响应类型为 JSON
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");

            // 将 JSON 数据发送到客户端
            res.getWriter().write(json);
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return;
    }

    /******************************正則表達式區*********************************/
    // 只包含中文字符、英文大小寫、數字及下劃線
    private final Pattern inputRegex = Pattern.compile("^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$");
    // 密碼格式(至少包含一個大寫字母、一個小寫字母、一個數字、一個特殊字符)
    private final Pattern passwordRegex = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,12}$");
    // 只能輸入中文格式
    private final Pattern hzRegex = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
    // 只能輸入數字格式
    private final Pattern numberRegex = Pattern.compile("^[0-9]+$");
    // email格式
    private final Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    /******************************參數驗證區***********************************/
    // 驗證是否為空值或空字串
    private Boolean validateSpace(String requestParameter) {
        return requestParameter == null || requestParameter.trim().length() == 0;
    }

    // 驗證輸入內容格式
    private Boolean validateInput(String input) {
        return inputRegex.matcher(input).find();
    }

    // 驗證密碼格式
    private Boolean validatePassword(String password) {
        return passwordRegex.matcher(password).find();
    }

    // 驗證中文格式
    private Boolean hzValidation(String hz) {
        return hzRegex.matcher(hz).find();
    }

    // 驗證數字格式
    private Boolean numberValidation(String number) {
        return numberRegex.matcher(number).find();
    }

    // 驗證信箱格式
    private Boolean emailValidation(String email) {
        return emailRegex.matcher(email).find();
    }

    private
}
