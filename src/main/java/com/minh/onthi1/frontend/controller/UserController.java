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

@WebServlet(name = "UserController", urlPatterns = "/user")

public class UserController extends HttpServlet {
    private ProductRepository productRepository;
    public UserController(){
        productRepository=new ProductRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> product=productRepository.getAllProduct();
        req.setAttribute("product",product);
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}
