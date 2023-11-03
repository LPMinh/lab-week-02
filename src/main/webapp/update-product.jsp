<%@ page import="com.minh.onthi1.backend.services.ProductService" %>
<%@ page import="com.minh.onthi1.backend.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="com.minh.onthi1.backend.models.ProductImage" %>
<%@ page import="com.minh.onthi1.backend.repositories.ProductImageRepository" %>
<%@ page import="com.minh.onthi1.backend.repositories.ProductDetailRepository" %>
<%@ page import="com.minh.onthi1.backend.models.ProductDetail" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 10/25/2023
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update-Product</title>
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>

        label{
            color: white;
            font-weight: bold;
        }

        .form{
            margin: 10px;
            padding: 10px;
        }
        .form-control-lg{
            margin: 10px;
            padding: 10px;
        }
        .container{
            margin: 10px;
            padding: 10px;
        }
        body {
            background: linear-gradient(90deg, #e74c3c, #2c3e50);

        }
        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }


        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<% Long Id = Long.parseLong(request.getParameter("id"));
    ProductService service = new ProductService();
    Product product = service.findProductByID(Id);
    double price = service.findPriceByProductID(Id);
%>
<div class="container">
    <h3>Thông tin cơ bản</h3>
    <form class="form row border bg-dark" action="control-servlet?action=update-product&id=<%=product.getProductId()%>" method="post">
        <div class="form-group row">
            <label class="col-3">ID</label>
            <input type="text" value="<%=product.getProductId()%>" disabled class="col-6">
        </div>
        <div class="form-group row">
            <label class="col-3">name</label>
            <input type="text" name="name" value="<%=product.getName()%>" class="col-6">
        </div>
        <div class="form-group row">
            <label class="col-3">description</label>
            <input type="text" name="des" value="<%=product.getDescription()%>"  class="col-6">
        </div>
        <div class="form-group row">
            <label class="col-3">unit</label>
            <input type="text" name="unit" value="<%=product.getUnit()%>" class="col-6">
        </div>
        <div class="form-group row">
            <label class="col-3">Manufacturer</label>
            <input class="col-6" type="text" name="manu" value="<%=product.getManufacturerName()%>">
        </div>
        <div class="form-group row">
            <label class="col-3"> Price</label>
            <input class="col-6" type="text" name="price" value="<%=price%>">
        </div>
        <div class="form-group row">
            <label class="col-3">Status</label>
            <select class="col-6" name="status">
                <option value="-1" <%=(product.getStatus().getCode()==-1)?"selected":""%> >ngưng bán</option>
                <option value="0"  <%=(product.getStatus().getCode()==0)?"selected":""%>>Hết hàng</option>
                <option value="1"  <%=(product.getStatus().getCode()==1)?"selected":""%>>Còn hàng</option>
            </select>
        </div>
        <div class="form-group row">
            <label class="col-3">Category</label>
            <select name="category" class="col-6">
                <option value="1" <%=(product.getProductCategory().getId()==1?"selected":"")%>>Dress</option>
                <option value="2" <%=(product.getProductCategory().getId()==2?"selected":"")%>>Shirt</option>
                <option value="3" <%=(product.getProductCategory().getId()==3?"selected":"")%>>Paint</option>
            </select>
        </div>
        <input type="submit" class="btn btn-primary" value="update">
    </form>
    <div class="border">
        <h3>Quản lý Hình Ảnh</h3>
        <form class="form" action="control-servlet?action=add-image&id=<%=product.getProductId()%>" method="post">
            <input type="text" name="image" placeholder="nhập url hình ảnh">
            <button type="submit" class="btn btn-primary" value="add image">Add Image</button>
        </form>
        <table class="table table-hover table-dark table-responsive table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Button</th>
                </tr>
            </thead>

            <tbody>
            <%
                ProductImageRepository imageRepository = new ProductImageRepository();
                List<ProductImage> listImage = imageRepository.getImageByIDProduct(product.getProductId());
                for (ProductImage image : listImage) {
            %>
                <tr>
                    <td><%=image.getImage_id()%></td>
                    <td><img src="<%=image.getPath()%>" height="100" width="100"></td>
                    <td>
                        <form action="control-servlet?action=remove-image&id=<%=image.getImage_id()%>&productID=<%=product.getProductId()%>" method="post">
                            <button type="submit" class="btn btn-danger">Xóa</button>
                        </form>
                    </td>
                </tr>
            <%}%>
            </tbody>
        </table>
    </div>



    <div class="border">
        <h3>Quản lý Chi Tiết Sản Phẩm</h3>
        <form class="bg-dark" action="control-servlet?action=add-product-detail" method="post">
            <input type="hidden" name="id" value="<%=product.getProductId()%>">
            <div class="form-control-lg ">
                <label>Màu</label>
                <Select name="color">
                    <!-- hiển thị màu RED,GREEN,BLUE,YELLOW,BLACK,BROWN,WHITE,ORANGE,PINK,PURPLE,GREY theo value từ 0 !-->
                    <option value="0">RED</option>
                    <option value="1">GREEN</option>
                    <option value="2">BLUE</option>
                    <option value="3">YELLOW</option>
                    <option value="4">BLACK</option>
                    <option value="5">BROWN</option>
                    <option value="6">WHITE</option>
                    <option value="7">ORANGE</option>
                    <option value="8">PINK</option>
                    <option value="9">PURPLE</option>
                    <option value="10">GREY</option>
                </Select>
            </div>
            <div class="form-control-lg">
                <label>Kích thước</label>
                <!-- hiển thị kích thước S,M,L,XL,XXL,XXXL theo value từ 0 !-->
                <Select name="size">
                    <option value="0">S</option>
                    <option value="1">M</option>
                    <option value="2">L</option>
                    <option value="3">XL</option>
                    <option value="4">XXL</option>
                    <option value="5">XXXL</option>
            </Select>
            </div>
            <div class="form-control-lg">
                <label>Số lượng</label>
                <input type="number" name="quantity">
            </div>
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
        <table class="table table-danger table-hover table-striped table-responsive">
            <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Màu
                    </th>
                    <th>
                        Kích thước
                    </th>
                    <th>
                        Số lượng
                    </th>
                </tr>
            </thead>
            <tbody>
                <%
                    ProductDetailRepository productDetailRepository=new ProductDetailRepository();
                    List<ProductDetail> productDetails=productDetailRepository.getProductDetailbyIDProduct(product.getProductId());
                    if(productDetails==null){
                        productDetails=new ArrayList<ProductDetail>();
                    }
                    for (ProductDetail productDetail:productDetails) {
                %>
                <tr>
                    <td>
                        <%=productDetail.getId()%>
                    </td>
                    <td>
                        <%=productDetail.getColor()%>
                    </td>
                    <td>
                        <%=productDetail.getId()%>
                    </td>
                    <td>
                        <%=productDetail.getQuantity()%>
                    </td>
                </tr>
            <%}%>
            </tbody>

        </table>
    </div>
    <div>
    </div>


</div>
</body>
</html>
