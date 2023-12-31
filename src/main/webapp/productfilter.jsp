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
</head>

<body>
<%
  ProductService productService=new ProductService();
  List<Product> listpro = (List<Product>) request.getAttribute("products");

%>
<div class="container">
  <div class="row col-12">
    <a class="btn btn-primary" href="add-product.jsp">Thêm sản phẩm</a>
    <form class="row" action="control-servlet?action=filter" method="get">
      <div class="col-sm-11">
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
      </div>
      <input type="hidden" name="action" value="filter">
      <input type="submit" class="btn btn-primary" value="Lọc">
    </form>
    <form class="row" action="control-servlet" method="get">
      <input type="text" name="query" placeholder="Nhập nội dung tìm kiếm" class="col-4">
      <input type="hidden" name="action" value="search">
      <button type="submit" class="btn btn-warning col-1">TÌM KIẾM</button>
    </form>
  </div>

  <table class="table table-hover table-primary">
    <thead>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>description</th>
    <th>unit</th>
    <th>manufacturer</th>
    <th>type</th>
    <th>status</th>
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
      <td><%=product.getProductCategory().getCategoryName()%></td>
      <td><%=product.getStatus()%>
      </td>
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
