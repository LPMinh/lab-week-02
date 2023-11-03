package com.minh.onthi1.frontend.model;

import com.minh.onthi1.backend.models.Customer;
import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.repositories.CustomerRepository;
import com.minh.onthi1.backend.repositories.ProductRepository;
import com.minh.onthi1.backend.services.ServiceCustomer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class CustomerModel {
    private ServiceCustomer serviceCustomer;
    public CustomerModel(){
       serviceCustomer=new ServiceCustomer();
    }
    public void getAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> custs=serviceCustomer.getAllCustomer();
        req.setAttribute("custs",custs);
        req.getRequestDispatcher("customer.jsp").forward(req,resp);
    }
    public void addCustomer(HttpServletRequest req,HttpServletResponse resp) throws IOException {

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String address=req.getParameter("address");
        String phone=req.getParameter("phone");
        Customer cus=new Customer(1,name,email,phone,address);
        serviceCustomer.addCustomer(cus);
        resp.sendRedirect("customer.jsp");

    }
    public void updateCustomer(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Long id=Long.parseLong(req.getParameter("id"));
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String address=req.getParameter("address");
        String phone=req.getParameter("phone");
        Customer cus=new Customer(id,name,email,phone,address);
        serviceCustomer.updateCustomer(cus);
        resp.sendRedirect("customer.jsp");

    }
    public void getCustomerByQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query=req.getParameter("query");
        List<Customer> custs=serviceCustomer.findByQuery(query);
        req.setAttribute("custs",custs);
        req.getRequestDispatcher("customer.jsp").forward(req,resp);
    }
}
