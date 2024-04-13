<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ren.product.service.*" %>
<%@ page import="com.ren.product.model.ProductVO" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ProductServiceImpl productSvc = new ProductServiceImpl();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list", list);
%>


<html>
<head>
    <title>所有員工資料 - listAllProduct.jsp</title>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

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
            width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>所有商品資料 - listAllProduct.jsp</h3>
            <h4><a href="select_product.jsp"><i class="fa-solid fa-house">回首頁</i></a></h4>
        </td>
    </tr>
</table>


<table id="sort-table" data-toggle="table">
    <thead>
    <tr>
        <th data-field="pNo" data-sortable="true">商品編號</th>
        <th data-field="pCatNo" data-sortable="true">商品類別編號</th>
        <th data-field="pName" data-sortable="true">商品名稱</th>
        <th data-field="pInfo" data-sortable="true">商品資訊</th>
        <th data-field="pSize" data-sortable="true">尺寸</th>
        <th data-field="pColor" data-sortable="true">顏色</th>
        <th data-field="pPrice" data-sortable="true">商品單價</th>
        <th data-field="pStat" data-sortable="true">商品狀態</th>
        <th data-field="pSalQty" data-sortable="true">商品已售出數量</th>
        <th data-field="pComPeople" data-sortable="true">評價總人數</th>
        <th data-field="pComScore" data-sortable="true">評價總星數</th>
        <th data-field="Correct" data-sortable="true">修改</th>
        <th data-field="Delete" data-sortable="true">刪除</th>
    </tr>
    </thead>
    <%@ include file="page1.file" %>
    <c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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
    </c:forEach>
</table>
<%@ include file="page2.file" %>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>

<script>

    $('#sort-table').bootstrapTable();

    function confirmDelete() {
        if (confirm("確定要刪除嗎？")) {
            document.getElementById("deleteForm").submit(); // 提交表?
            alert("刪除成功！"); // 彈出成功訊息!
        }
    }
</script>

</body>
</html>