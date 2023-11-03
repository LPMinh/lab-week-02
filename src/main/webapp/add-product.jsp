<%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/25/2023
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add-product
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form class="form" action="control-servlet?action=add-product" method="post">
        <input type="text" name="name" placeholder="Tên">
        <input type="text" name="price" placeholder="Giá">
        <input type="text" name="description" placeholder="Mô tả">
        <input type="text" name="unit" placeholder="Đơn vị">
        <input type="text" name="manu" placeholder="Nhà sản xuất">
        <select name="status">
            <option value="-1">ngưng bán</option>
            <option value="0">Hết hàng</option>
            <option value="1">Còn hàng</option>
        </select>

        <select name="category">
            <option value="1">Dress</option>
            <option value="2">Shirt</option>
            <option value="3">Paint</option>
        </select>
        <input type="submit" class="btn btn-primary" value="Thêm">
    </form>
</div>
</body>
</html>
