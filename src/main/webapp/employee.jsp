<%@ page import="com.minh.onthi1.backend.services.EmployeeService" %>
<%@ page import="com.minh.onthi1.backend.models.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/25/2023
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <%
            EmployeeService emp=new EmployeeService();
            List<Employee> list= (List<Employee>) request.getAttribute("emps");
            if(list==null){
                list=emp.getAllEmployee();
            }
        %>
        <table class="table table-hover table-stripped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>full name</th>
                    <th>dob</th>
                    <th>email</th>
                    <th>phone</th>
                    <th>address</th>
                    <th>status</th>
                </tr>
            </thead>
            <tbody>
                <% for(Employee employee : list){%>
                <tr>
                    <td><%=employee.getEmp_id()%></td>
                    <td><%=employee.getFullName()%></td>
                    <td><%=employee.getDob()%></td>
                    <td><%=employee.getEmail()%></td>
                    <td><%=employee.getAddress()%></td>
                    <td><%=employee.getStatus().getDescription()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>
