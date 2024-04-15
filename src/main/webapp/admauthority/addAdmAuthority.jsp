<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.ren.product.model.AdmAuthorityVO" %>

<% //見com.product.com.controller.ProductServlet.java第238行存入req的admAuthorityVO物件 (此為輸入格式有錯誤時的admAuthorityVO物件)
   AdmAuthorityVO admAuthorityVO = (AdmAuthorityVO) request.getAttribute("admAuthorityVO");
%>
--<%= admAuthorityVO==null %>--${admAuthorityVO.pCatNo}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addAdmAuthority.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addAdmAuthority.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="admAuthority.do" name="form1">
<table>
	
	
	
	
<!-- 	<tr> -->
<!-- 		<td>商品編號:</td> -->
<%-- 		<td><input type="TEXT" name="pNo" value="<%= (admAuthorityVO==null)? "10000001" : admAuthorityVO.getpNo()%>" size="45"/></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pName" value="<%= (admAuthorityVO==null)? "短襯衫" : admAuthorityVO.getpName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="pInfo" value="<%= (admAuthorityVO==null)? "短短的襯衫" : admAuthorityVO.getpInfo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>尺寸:</td>
		<td><input type="TEXT" name="pSize" value="<%= (admAuthorityVO==null)? "1" : admAuthorityVO.getpSize()%>" size="45"/></td>
	</tr>
	<tr>
		<td>顏色:</td>
		<td><input type="TEXT" name="pColor" value="<%= (admAuthorityVO==null)? "藍色" : admAuthorityVO.getpColor()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品單價:</td>
		<td><input type="TEXT" name="pPrice" value="<%= (admAuthorityVO==null)? "5000" : admAuthorityVO.getpPrice()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="pStat" value="<%= (admAuthorityVO==null)? "1" : admAuthorityVO.getpStat()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品已售出數量:</td>
		<td><input type="TEXT" name="pSalQty" value="<%= (admAuthorityVO==null)? "200" : admAuthorityVO.getpSalQty()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總人數:</td>
		<td><input type="TEXT" name="pComPeople" value="<%= (admAuthorityVO==null)? "50" : admAuthorityVO.getpComPeople()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總星數:</td>
		<td><input type="TEXT" name="pComScore" value="<%= (admAuthorityVO==null)? "3" : admAuthorityVO.getpComScore()%>" size="45"/></td>
	</tr>					

	<jsp:useBean id="productCategorySvc" scope="page" class="com.ren.productcategory.service.ProductCategoryServiceImpl" />
	<tr>
		<td>商品類別編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="pCatNo">
			<c:forEach var="productCategoryVO" items="${productCategorySvc.all}">
				<option value="${productCategoryVO.pCatNo}" ${(admAuthorityVO.pCatNo==productCategoryVO.pCatNo)? 'selected':'' } >${productCategoryVO.pCatName}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>