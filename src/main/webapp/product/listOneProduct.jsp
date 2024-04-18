<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ren.product.service.*" %>
<%@ page import="com.ren.product.model.ProductVO" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java(Concroller), 存入req的productVO物件
%>

<html>
<head>
    <title>商品資料 - listOneProduct.jsp</title>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous"/>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/components/hamburger.css"/>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 600px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid #CCCCFF;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body bgcolor='white'>

<!-- 導覽列 -->
<nav>
    <div>FallElove</div>
    <input id="hamburger" type="checkbox">
    <label class="hamburger-icon" for="hamburger">
        <div class="bar"></div>
    </label>
    <ul class="menu">
        <li><a href="<%=request.getContextPath()%>/administrator/select_administrator.jsp">管理員</a></li>
        <li><a href="<%=request.getContextPath()%>/admauthority/select_admAuthority.jsp">管理員權限</a></li>
        <li><a href="<%=request.getContextPath()%>/authorityfunction/select_authorityFunction.jsp">功能權限</a></li>
        <li><a href="<%=request.getContextPath()%>/title/select_title.jsp">職位</a></li>
        <li><a href="<%=request.getContextPath()%>/product/select_product.jsp">商品</a></li>
        <li><a href="<%=request.getContextPath()%>/productcategory/select_productCategory.jsp">商品種類</a></li>
    </ul>
</nav>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>單筆商品資料</h3>
            <h4><a class="icon-link" href="select_product.jsp">
                <i class="fa-solid fa-house">首頁</i>
            </a></h4>
        </td>
    </tr>
</table>

<div style="display: flex; flex-direction: row;">
    <table>
        <tr>
            <th>商品編號</th>
            <th>商品類別編號</th>
            <th>商品名稱</th>
            <th>商品資訊</th>
            <th>尺寸</th>
            <th>顏色</th>
            <th>商品單價</th>
            <th>商品狀態</th>
            <th>商品已售出數量</th>
            <th>評價總人數</th>
            <th>評價總星數</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        <tr>
            <td>${productVO.pNo}</td>
            <td>${productVO.pCatNo}</td>
            <td>${productVO.pName}</td>
            <td>${productVO.pInfo}</td>
            <td>${productVO.pSize}</td>
            <td>${productVO.pColor}</td>
            <td>${productVO.pPrice}</td>
            <td>${productVO.pStat}</td>
            <td>${productVO.pSalQty}</td>
            <td>${productVO.pComPeople}</td>
            <td>${productVO.pComScore}</td>
            <td>
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do"
                      style="margin-bottom: 0px;">
                    <button type="submit">
                        <i class="fa-solid fa-pen-to-square"></i>
                        <input type="hidden" name="pNo" value="${productVO.pNo}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                </form>
            </td>
            <td>
                <form id="deleteForm" method="post" action="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
                    <button type="button" onclick="confirmDelete()">
                        <i class="fa-solid fa-trash-can"></i>
                        <input type="hidden" name="pNo" value="${productVO.pNo}">
                        <input type="hidden" name="action" value="delete">
                    </button>
                </form>
            </td>
        </tr>
    </table>
</div>

<script>
    function confirmDelete() {
        if (confirm("確定要刪除嗎？")) {
            document.getElementById("deleteForm").submit(); // 提交表?
            alert("刪除成功！"); // 彈出成功訊息!
            // window.location.href = "select_product.jsp"; // 重定向到首?
        }
    }
</script>

</body>

</html>