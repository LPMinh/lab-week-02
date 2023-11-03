package com.minh.onthi1.frontend.controller;

import com.minh.onthi1.backend.models.Customer;
import com.minh.onthi1.frontend.model.CustomerModel;
import com.minh.onthi1.frontend.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controllservlet", urlPatterns = "/control-servlet")
public class ControllServlet extends HttpServlet {
    private String action;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action= req.getParameter("action");
        if(action.equals("filter")){
            ProductModel productModel = new ProductModel();
            productModel.filterProduct(req,resp);

        }else if(action.equals("search")){
            ProductModel productModel = new ProductModel();
            productModel.SearchProduct(req,resp);
        }else if(action.equals("search-customer")){
            CustomerModel model=new CustomerModel();
            model.getCustomerByQuery(req,resp);

        }else if(action.equals("search-employee")) {

        }else if(action.equals("filter-employee")){

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action = req.getParameter("action");

        if (action.equals("add-product")) {
            ProductModel productModel = new ProductModel();
            productModel.addProduct(req, resp);
        } else if (action.equals("update-product")) {
            ProductModel productModel = new ProductModel();
            productModel.updateProduct(req, resp);
        } else if (action.equals("delete-product")) {
            ProductModel productModel = new ProductModel();
            productModel.deleteProduct(req, resp);
        }  else if(action.equals("add-customer")){
            CustomerModel model=new CustomerModel();
            model.addCustomer(req,resp);
        }   else if(action.equals("update-customer")){
            CustomerModel model=new CustomerModel();
            model.updateCustomer(req,resp);

        } else if(action.equals("add-image")){
            ProductModel productModel = new ProductModel();
            productModel.addImage(req,resp);
        }
         else if(action.equals("remove-image")){
            ProductModel productModel = new ProductModel();
            productModel.removeImage(req,resp);
        }else if(action.equals("add-product-detail")){
             ProductModel productModel = new ProductModel();
             productModel.addProductDetail(req,resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
