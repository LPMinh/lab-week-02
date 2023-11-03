package com.minh.onthi1.frontend.controller;

import com.minh.onthi1.backend.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductDetailController", urlPatterns = "/product-detail")

public class ProductDetailController extends HttpServlet {
    private ProductRepository productRepository=new ProductRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id= Long.valueOf(req.getParameter("id"));
        req.setAttribute("product",productRepository.getProductByID(id));
        req.getRequestDispatcher("product-detail.jsp").forward(req,resp);

    }
}
