<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ren.product.model.ProductVO" %>

<% //見com.product.com.controller.ProductServlet.java第238行存入req的productVO物件 (此為輸入格式有錯誤時的productVO物件)
   ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
--<%= productVO==null %>--${productVO.pCatNo}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addProduct.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
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

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addProduct.jsp</h3></td><td>
		 <h4><a href="select_product.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="product.do" name="form1">
<table>
	
	
	
	
<!-- 	<tr> -->
<!-- 		<td>商品編號:</td> -->
<%-- 		<td><input type="TEXT" name="pNo" value="<%= (productVO==null)? "10000001" : productVO.getpNo()%>" size="45"/></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pName" value="<%= (productVO==null)? "短襯衫" : productVO.getpName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="pInfo" value="<%= (productVO==null)? "短短的襯衫" : productVO.getpInfo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>尺寸:</td>
		<td><input type="TEXT" name="pSize" value="<%= (productVO==null)? "1" : productVO.getpSize()%>" size="45"/></td>
	</tr>
	<tr>
		<td>顏色:</td>
		<td><input type="TEXT" name="pColor" value="<%= (productVO==null)? "藍色" : productVO.getpColor()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品單價:</td>
		<td><input type="TEXT" name="pPrice" value="<%= (productVO==null)? "5000" : productVO.getpPrice()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="pStat" value="<%= (productVO==null)? "1" : productVO.getpStat()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品已售出數量:</td>
		<td><input type="TEXT" name="pSalQty" value="<%= (productVO==null)? "200" : productVO.getpSalQty()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總人數:</td>
		<td><input type="TEXT" name="pComPeople" value="<%= (productVO==null)? "50" : productVO.getpComPeople()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總星數:</td>
		<td><input type="TEXT" name="pComScore" value="<%= (productVO==null)? "3" : productVO.getpComScore()%>" size="45"/></td>
	</tr>					

	<jsp:useBean id="productCategorySvc" scope="page" class="com.ren.productcategory.service.ProductCategoryServiceImpl" />
	<tr>
		<td>商品類別編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="pCatNo">
			<c:forEach var="productCategoryVO" items="${productCategorySvc.all}">
				<option value="${productCategoryVO.pCatNo}" ${(productVO.pCatNo==productCategoryVO.pCatNo)? 'selected':'' } >${productCategoryVO.pCatName}
			</c:forEach>
		</select></td>
	</tr>

</table>

<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>

</html>