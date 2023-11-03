<%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/26/2023
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add-customer</title>
</head>
<body>
  <div class="container">
    <form class="form" action="control-servlet?action=add-customer" method="post">
        <input name="name" type="text" placeholder="nhập tên">
        <input name="email" type="text" placeholder="nhập email">
        <input name="address" type="text" placeholder="nhập địa chỉ">
        <input name="phone" type="text" placeholder="nhập số điện thoại">
        <button type="submit" class="btn btn-primary"></button>
    </form>
  </div>
</body>
</html>
