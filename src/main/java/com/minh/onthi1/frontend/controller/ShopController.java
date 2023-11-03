package com.minh.onthi1.frontend.controller;

import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "shop",urlPatterns = "/shop")

public class ShopController extends HttpServlet {
    private ProductRepository productRepository;
    public ShopController(){
        productRepository=new ProductRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Product> products=productRepository.getAllProduct();
            req.setAttribute("product",products);
            req.getRequestDispatcher("shop.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
