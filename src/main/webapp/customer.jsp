<%@ page import="com.minh.onthi1.backend.models.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.minh.onthi1.backend.services.ServiceCustomer" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/25/2023
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Khach Hang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <form class="form" action="control-servlet" method="get">
                <input type="text" name="query" placeholder="Tìm tên sản phẩm hoặc ID">
                <input type="hidden" name="action" value="search-customer">
                <button type="submit" class="btn btn-primary">TÌM</button>
            </form>
            <a href="add-customer.jsp" class="btn btn-primary">Thêm khách hàng</a>
        </div>

        <table class="table table-hover table-primary table-stripped">
            <thead>
                <td>
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>email</th>
                        <th>phone</th>
                        <th>address</th>
                        <th>button</th>
                    </tr>
                </td>
            </thead>
            <tbody>
                <%
                    ServiceCustomer serviceCustomer=new ServiceCustomer();
                    List<Customer> list= (List<Customer>) request.getAttribute("custs");
                    if(list==null){
                        list=serviceCustomer.getAllCustomer();
                    }
                    for (Customer c:list){
                %>
                    <tr>
                    <td><%=c.getCustId()%></td>
                    <td><%=c.getCustName()%></td>
                    <td><%=c.getEmail()%></td>
                    <td><%=c.getPhone()%></td>
                    <td><%=c.getAddress()%></td>
                    <td>
                        <a href="update-customer.jsp?id=<%=c.getCustId()%>" class="btn btn-primary">Sửa</a>
                        <a href="control-servlet?action=delete&id=<%=c.getCustId()%>" class="btn btn-danger">Xóa</a>
                    </td>
                     </tr>
                <%}%>
            </tbody>

        </table>
    </div>
</body>
</html>
