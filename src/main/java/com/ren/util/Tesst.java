package com.ren.util;

import javax.servlet.RequestDispatcher;
import java.math.BigDecimal;

public class Tesst {
    public static String Validation (String str) {

        String value = req.getParameter(str);
        if (value == null || (value.trim()).length() == 0) {
            errorMsgs.add("請輸入員工編號");
        }
        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req.getRequestDispatcher("/product/select_product.jsp");
            failureView.forward(req, res);
            return;// 程式中斷
        }

        Integer pNo = null;
        try {
            pNo = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("員工編號格式不正確");
        }

        String pName = req.getParameter("pName");
        String pNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (pName == null || pName.trim().length() == 0) {
            errorMsgs.add("商品名稱: 請勿空白");
        } else if (!pName.trim().matches(pNameReg)) { // 以下練習正則(規)表示式(regular-expression)
            errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

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

        return ;
    }
}
