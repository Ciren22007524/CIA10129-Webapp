<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>fallElove Product Manage</title>

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
            width: 450px;
            background-color: #CCCCFF;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
            text-align: center;
        }

        table#table-1 h4 {
            color: blue;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

</head>

<body bgcolor='white'>

<!-- 定義一個 <img> 元素，並將其 src 屬性設置為 Base64 編碼的字串 -->
<img id="base64Image" src="" alt="Base64 Image"/>

<table id="table-1">
    <tr>
        <td>
            <h3>商品管理頁面</h3>
            <h4>
                <a class="icon-link" href="select_administrator.jsp">
                    <i class="fa-solid fa-house">首頁</i>
                </a>
            </h4>
        </td>
    </tr>
</table>

<p>This is the Home page for FallELove Product Management</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color: red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color: red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='listAllAdministrator.jsp'>List</a> all Products. <br> <br></li>


    <li>
        <FORM METHOD="post" ACTION="administrator.do">
            <b>輸入商品編號 (從1開始):</b> <input type="text" name="admNo">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <jsp:useBean id="administratorSvc" scope="page"
                 class="com.ren.administrator.service.AdministratorServiceImpl"/>

    <li>
        <FORM METHOD="post" ACTION="administrator.do">
            <b>選擇商品編號:</b> <select size="1" name="admNo">
            <c:forEach var="adminitratorVO" items="${administratorSvc.all}">
            <option value="${adminitratorVO.admNo}">${adminitratorVO.admNo}
                </c:forEach>
        </select> <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="administrator.do">
            <b>選擇商品名稱:</b> <select size="1" name="admNo">
            <c:forEach var="adminitratorVO" items="${administratorSvc.all}">
            <option value="${adminitratorVO.admNo}">${adminitratorVO.admName}
                </c:forEach>
        </select> <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
</ul>


<h3>商品新增</h3>
<ul>
    <li><a href='addAdministrator.jsp'>Add</a> a new Product.</li>
</ul>

<script>
    // 從後端獲取 Base64 編碼的圖像數據
    // 假設後端返回的數據為一個 JSON 對象，其中包含 Base64 編碼的圖像
    fetch('your_api_endpoint')
        .then(response => response.json())
        .then(data => {
            // 獲取 Base64 編碼的圖像
            const base64Image = data.base64Image;

            // 設置 <img> 元素的 src 屬性為 Base64 編碼的字串
            document.getElementById('base64Image').src = 'data:image/jpeg;base64,' + base64Image;
        })
        .catch(error => console.error('Error fetching Base64 image:', error));
</script>
</body>
</html>