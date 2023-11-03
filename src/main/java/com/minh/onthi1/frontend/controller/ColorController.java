package com.minh.onthi1.frontend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minh.onthi1.backend.enums.Size;
import com.minh.onthi1.backend.repositories.ProductDetailRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.weld.context.http.Http;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ColorController", urlPatterns = "/color")

public class ColorController extends HttpServlet {
    private ProductDetailRepository productDetailRepository;

    public ColorController(){
        productDetailRepository=new ProductDetailRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idColor= Integer.parseInt(req.getParameter("idColor"));
        Long idProduct= Long.parseLong(req.getParameter("idProduct"));
        List<Size> sizes=productDetailRepository.getSizeByColor(idColor,idProduct);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(sizes);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);



    }



}
