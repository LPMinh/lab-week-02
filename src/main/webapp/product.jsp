<%@ page import="com.minh.onthi1.backend.services.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.minh.onthi1.backend.models.Product" %>
<%@ page import="com.minh.onthi1.backend.models.ProductStatus" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/25/2023
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>

        .form {
            margin: 0 auto;
            width: 50%;
            padding: 20px;
            border: 1px solid #B0C4DE;
            background: white;
        }
        đổi màu chữ thành trắng
        .form label {
            color: white;
        }

        .btn {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: green;
        }

        .btn-primary {
            background-color: #4CAF50;
        }

       căn chỉnh text field cho đồng đều
        .form-control {
            display: block;
            width: 100%;
            height: calc(1.5em + 0.75rem + 2px);
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;
            color: #212529;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .form-search {
            margin: 0 auto;
            width: 50%;
            padding: 20px;
            border: 1px solid #B0C4DE;
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0)), url("https://images.unsplash.com/photo-1627310000415-8b8b5b5b5b5b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2FuaXRhcmlvJTIwcHJvZHVjdHN8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }


        .btn {
            width: 100%;
        }

        .form {
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0)), url("https://images.unsplash.com/photo-1627310000415-8b8b5b5b5b5b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2FuaXRhcmlvJTIwcHJvZHVjdHN8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }

        .btn-danger {
            background-color: #f44336;
        }


        .table-hover tbody tr:hover {
            color: #212529;
            background-color: rgba(0, 0, 0, 0.075);
        }
        body {
            background: linear-gradient(90deg, #e74c3c, #2c3e50);

        }





    </style>

</head>

<body>
<%
    ProductService productService = new ProductService();
    List<Product> listpro = productService.getAllProductsByStauts(ProductStatus.ACTIVE);

%>
<div class="container">
    <div class="row col-12">
        <a class="btn btn-primary" href="add-product.jsp">Thêm sản phẩm</a>
        <form class="form" action="control-servlet?action=filter" method="get">
                <div class="form-group row mt-3">
                    <label class="col-2">Trạng thái</label>
                    <select name="status" class="col-10">
                        <option value="-1">ngưng bán</option>
                        <option value="0">Hết hàng</option>
                        <option value="1">Còn hàng</option>
                    </select>
                </div>
                <div class="form-group row mt-3">
                    <label class="col-2">Sắp xếp</label>
                    <select name="sort" class="col-10">
                        <option value="desc">Cũ nhất</option>
                        <option value="asc">Mới nhất</option>
                    </select>
                </div>
                <input type="hidden" name="action" value="filter">

                <button type="submit" class="btn btn-primary">Lọc</button>
        </form>
        <form class="row form-search" action="control-servlet" method="get">
            <input type="text" name="query" placeholder="Nhập nội dung tìm kiếm" class="col-12">
            <input type="hidden" name="action" value="search">
            <button  type="submit" class="btn btn-warning col-1">TÌM KIẾM</button >
        </form>
    </div>

    <table class="table table-hover table-striped table-danger">
        <thead>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>description</th>
        <th>unit</th>
        <th>manufacturer</th>
        <th>status</th>
        <th>type</th>
        <th>button</th>
        </thead>
        <tbody>

        <%

            for (Product product : listpro) {
                double price = productService.findPriceByProductID(product.getProductId());
        %>
        <tr>
            <td><%=product.getProductId()%>
            </td>
            <td><%=product.getName()%>
            </td>
            <td><%=price%>
            </td>
            <td><%=product.getDescription()%>
            </td>
            <td><%=product.getUnit()%>
            </td>
            <td><%=product.getManufacturerName()%>
            </td>
            <td><%=product.getStatus()%>
            </td>
            <td><%=product.getProductCategory().getCategoryName()%></td>
            <td><a href="update-product.jsp?id=<%=product.getProductId()%>" class="btn btn-primary">Update</a>
                <form action="control-servlet?action=delete-product&id=<%=product.getProductId()%>" method="post">
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>

            </td>
        </tr>
        <%}%>

        </tbody>
    </table>
</div>
</body>
</html>
