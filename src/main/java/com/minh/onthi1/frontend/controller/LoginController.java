package com.minh.onthi1.frontend.controller;

import com.minh.onthi1.backend.models.Account;
import com.minh.onthi1.backend.repositories.AccountRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private AccountRepository accountRepository;
    public LoginController(){
        accountRepository=new AccountRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("pass");
        Account account = accountRepository.auththenticAccount(email,password);
        if(account!=null){
//            HttpSession session=req.getSession();
//            session.setAttribute("idAcc",account.getAccountID());
            if(accountRepository.authorizeAccount(account.getAccountID())){
                resp.sendRedirect("admin");
            }else{
                resp.sendRedirect("user");
            }
        }


    }
}
