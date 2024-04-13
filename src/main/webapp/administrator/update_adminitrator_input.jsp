<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ren.administratorcategory.service.*"%>
<%@ page import="com.ren.administratorcategory.model.*"%>
<%@ page import="com.ren.administrator.model.*" %>

<% //見com.administrator.com.controller.ProductServlet.java第163行存入req的adminitratorVO物件 (此為從資料庫取出的adminitratorVO, 也可以是輸入格式有錯誤時的adminitratorVO物件)
   AdministratorVO adminitratorVO = (AdministratorVO) request.getAttribute("adminitratorVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改 - update_administrator_input.jsp</title>

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
		 <h3>商品資料修改 - update_administrator_input.jsp</h3>
		 <h4><a href="select_administrator.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="administrator.do" name="form1">
<table>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="admNo" value="<%= (adminitratorVO==null)? "10000001" : adminitratorVO.getadmNo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="admName" value="<%= (adminitratorVO==null)? "短襯衫" : adminitratorVO.getadmName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="pInfo" value="<%= (adminitratorVO==null)? "短短的襯衫" : adminitratorVO.getpInfo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>尺寸:</td>
		<td><input type="TEXT" name="admStat" value="<%= (adminitratorVO==null)? "1" : adminitratorVO.getadmStat()%>" size="45"/></td>
	</tr>
	<tr>
		<td>顏色:</td>
		<td><input type="TEXT" name="admEmail" value="<%= (adminitratorVO==null)? "藍色" : adminitratorVO.getadmEmail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品單價:</td>
		<td><input type="TEXT" name="titleNo" value="<%= (adminitratorVO==null)? "5000" : adminitratorVO.gettitleNo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="admHireDate" value="<%= (adminitratorVO==null)? "1" : adminitratorVO.getadmHireDate()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品已售出數量:</td>
		<td><input type="TEXT" name="admPhoto" value="<%= (adminitratorVO==null)? "200" : adminitratorVO.getadmPhoto()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總人數:</td>
		<td><input type="TEXT" name="pComPeople" value="<%= (adminitratorVO==null)? "50" : adminitratorVO.getpComPeople()%>" size="45"/></td>
	</tr>
	<tr>
		<td>評價總星數:</td>
		<td><input type="TEXT" name="pComScore" value="<%= (adminitratorVO==null)? "3" : adminitratorVO.getpComScore()%>" size="45"/></td>
	</tr>					

	<jsp:useBean id="administratorCategorySvc" scope="page" class="com.ren.administratorcategory.service.ProductCategoryServiceImpl" />
	<tr>
		<td>商品類別編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="admPwd">
			<c:forEach var="administratorCategoryVO" items="${administratorCategorySvc.all}">
				<option value="${administratorCategoryVO.admPwd}" ${(adminitratorVO.admPwd==administratorCategoryVO.admPwd)? 'selected':'' } >${administratorCategoryVO.pCatName}
			</c:forEach>
		</select></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="admNo" value="<%=adminitratorVO.getadmNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>