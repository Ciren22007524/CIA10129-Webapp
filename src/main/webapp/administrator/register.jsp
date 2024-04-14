<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/gossip.css" type="text/css">
    <title>會員申請</title>
</head>
<body>

<h1>會員申請</h1>
<form method='post' name='action' value='register'>
    <table>

        <tr>
            <td>名稱（最大 16 字元）：</td>
            <td><input type='text' name='username' size='25' maxlength='16'></td>
            <td><button></button></td>
        </tr>
        <tr>
            <td>密碼（8 到 16 字元）：</td>
            <td><input type='password' name='password' size='25' maxlength='16'></td>
            <td><button></button></td>
        </tr>
        <tr>
            <td>確認密碼：</td>
            <td><input type='password' name='password2' size='25' maxlength='16'></td>
        </tr>
        <tr>
            <td>郵件位址：</td>
            <td><input type='text' name='email' size='25' maxlength='100'></td>
            <td><button></button></td>
        </tr>
        <tr>
            <td colspan='2' align='center'><input type='submit' value='送出'></td>
        </tr>
    </table>
</form>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color: red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color: red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<script>

</script>

</body>
</html>