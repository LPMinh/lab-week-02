package com.minh.onthi1.frontend.model;

import com.minh.onthi1.backend.converters.ProductStatusConverter;
import com.minh.onthi1.backend.enums.Color;
import com.minh.onthi1.backend.enums.Size;
import com.minh.onthi1.backend.models.*;
import com.minh.onthi1.backend.repositories.ProductCategoryRepository;
import com.minh.onthi1.backend.repositories.ProductDetailRepository;
import com.minh.onthi1.backend.repositories.ProductImageRepository;
import com.minh.onthi1.backend.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductModel {
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ProductImageRepository productImageRepository;

    private ProductDetailRepository productDetailRepository;
    public ProductModel() {
        productRepository = new ProductRepository();
        productCategoryRepository = new ProductCategoryRepository();
        productImageRepository = new ProductImageRepository();
        productDetailRepository = new ProductDetailRepository();
    }

    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String unit = req.getParameter("unit");
        String manufacturerName = req.getParameter("manu");
        String price = req.getParameter("price");
        ProductStatus status = new ProductStatusConverter().convertToEntityAttribute(Integer.parseInt(req.getParameter("status")));
        ProductCategory productCategory = productCategoryRepository.findByID(Long.valueOf(req.getParameter("category")));
        Product product =new Product(0,name,description,unit,manufacturerName,status,productCategory);
        productRepository.insertProduct(product);
        resp.sendRedirect("product.jsp");

    }

    public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("des");
        String unit = req.getParameter("unit");
        String manufacturerName = req.getParameter("manu");
        double price = Double.parseDouble(req.getParameter("price"));
        ProductStatus status = new ProductStatusConverter().convertToEntityAttribute(Integer.parseInt(req.getParameter("status")));
        ProductCategory productCategory = productCategoryRepository.findByID(Long.valueOf(req.getParameter("category")));
        Product product = new Product(id, name, description, unit, manufacturerName, status, productCategory);
        ProductPrice productPrice = new ProductPrice(product, Date.valueOf(LocalDate.now()), "event", price);
        productRepository.update(product);
        productRepository.addProductPrice(productPrice);
        resp.sendRedirect("product.jsp");

    }

    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        productRepository.removeProduct(id);
        resp.sendRedirect("product.jsp");
    }

    public void filterProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sort = req.getParameter("sort");
        int status = Integer.parseInt(req.getParameter("status"));
        List<Product> prs= productRepository.filterProduct(status, sort);
        req.setAttribute("products",prs);
        req.getRequestDispatcher("productfilter.jsp").forward(req,resp);
    }
    public void SearchProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String query = req.getParameter("query");
        List<Product> prs= productRepository.searchProduct(query);
        req.setAttribute("products",prs);
        req.getRequestDispatcher("productfilter.jsp").forward(req,resp);
    }

    public void addImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getParameter("image");
        Long id= Long.valueOf(req.getParameter("id"));
        Product product=productRepository.getProductByID(id);
        ProductImage productImage=new ProductImage(product,1,path,"hinh anh");
        if(productImageRepository.addProductImage(productImage)){
            resp.sendRedirect("update-product.jsp?id="+id+"");
        }else{
            resp.sendRedirect("update-product.jsp?id="+id+"");
        }

    }

    public void removeImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Long id= Long.valueOf(req.getParameter("id"));
      Long idProduct= Long.valueOf(req.getParameter("productID"));
      productImageRepository.removeProductImage(id);
      resp.sendRedirect("update-product.jsp?id="+idProduct+"");
    }

    public void addProductDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id= Long.valueOf(req.getParameter("id"));
        Product product=productRepository.getProductByID(id);
        Color color=Color.getColorFromInt(Integer.parseInt(req.getParameter("color")));
        Size size=Size.getSizeFromInt(Integer.parseInt(req.getParameter("size")));
        int quantity=Integer.parseInt(req.getParameter("quantity"));
        ProductDetail productDetail=new ProductDetail(0L,product,color,size,quantity);
        productDetailRepository.inserProductDetail(productDetail);
        resp.sendRedirect("update-product.jsp?id="+id+"");
    }
}
