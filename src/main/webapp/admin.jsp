<%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/29/2023
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <style>

    .container {
        width: 50%;
        height: 50%;
        background: rgba(0, 0, 0, 0.5);
    }

    a {
        text-decoration: none;
        color: #fff;
        font-size: 20px;
        padding: 10px 15px;
        display: block;
        text-align: center;
        transition: 0.5s;
    }

    a:hover {
        background: #fff;
        color: #333;
        border-radius: 5px;
    }
    body {
        background: linear-gradient(90deg, #e74c3c, #2c3e50);
        height: 100vh;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }








    </style>
</head>
<body>
<div class="container">
    <a href="product.jsp">Quản lý Sản phẩm</a><br>
    <a href="customer.jsp">Quản lý Khách hàng</a><br>
    <a href="employee.jsp">Quản lý Nhân viên</a><br>
    <a href="employee.jsp">Quản lý Hóa đơn</a><br>
</div>


</body>
</html>
