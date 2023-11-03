<%@ page import="com.minh.onthi1.backend.services.ServiceCustomer" %>
<%@ page import="com.minh.onthi1.backend.models.Customer" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/26/2023
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update-customer</title>
</head>
<body>
    <%
        long id=Long.parseLong(request.getParameter("id"));
        ServiceCustomer serviceCustomer=new ServiceCustomer();
        Customer cus=serviceCustomer.findCustomerByID(id);

    %>
    <div class="container">
        <form class="form" action="control-servlet?action=update-customer" method="post">
            <div class="form-group">
                <label>ID</label>
                <input type="hidden" name="id" value="<%=cus.getCustId()%>">
                <input disabled="true" value="<%=cus.getCustId()%>">
            </div>
            <div class="form-group">
                <label>name</label>
                <input name="name" value="<%=cus.getCustName()%>">

            </div>
            <div class="form-group">
                <label>email</label>
                <input name="email" value="<%=cus.getEmail()%>">
            </div>
            <div class="form-group">
                <label>phone</label>
                <input name="phone" value="<%=cus.getPhone()%>">
            </div>
            <div class="form-group">
                <label>address</label>
                <input name="address" value="<%=cus.getAddress()%>">
            </div>
            <button type="submit" class="btn btn-primary">LƯU</button>
        </form>
    </div>
</body>
</html>
