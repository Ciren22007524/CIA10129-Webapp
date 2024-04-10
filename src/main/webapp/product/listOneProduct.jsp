<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ren.product.service.*" %>
<%@ page import="com.ren.product.model.ProductVO" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java(Concroller), 存入req的productVO物件
%>

<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>商品資料 - listOneProduct.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
</table>

</body>
</html>