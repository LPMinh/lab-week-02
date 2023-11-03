package com.minh.onthi1.frontend.controller;

import com.minh.onthi1.backend.models.Order;
import com.minh.onthi1.backend.models.OrderDetail;
import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", urlPatterns = {"/cart","/add-to-cart"})

public class CartController extends HttpServlet {
    private ProductRepository productRepository;
    public CartController(){
        productRepository=new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long proDuctID = Long.valueOf(req.getParameter("productID"));
        int quanlity = Integer.parseInt(req.getParameter("quanlity"));
        Product product=productRepository.findProductById(proDuctID).get();
        OrderDetail orderDetail=new OrderDetail();
        Order order=new Order();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quanlity);
        double price=product.getPrices().get(product.getPrices().size()).getPrice();
        orderDetail.setPrice(price*quanlity);

        // Kiểm tra xem người dùng đã có cookie giỏ hàng hay chưa
        Cookie[] cookies = req.getCookies();
        Cookie cartCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartCookie = cookie;
                    break;
                }
            }
        }

        // Tạo danh sách giỏ hàng hoặc sử dụng danh sách đã tồn tại trong cookie
        List<String> cartItems = new ArrayList<>();

        if (cartCookie != null) {
            // Giỏ hàng đã tồn tại trong cookie, ta cần chuyển nó thành danh sách
            String[] items = cartCookie.getValue().split(",");
            for (String item : items) {
                cartItems.add(item);
            }
        }

        // Thêm sản phẩm vào giỏ hàng
        cartItems.add(orderDetail.toString());

        // Chuyển danh sách giỏ hàng thành chuỗi và cập nhật cookie
        String cartValue = String.join(",", cartItems);
        cartCookie = new Cookie("cart", cartValue);
        resp.addCookie(cartCookie);

        // Chuyển người dùng trở lại trang chính hoặc trang giỏ hàng (tuỳ thuộc vào yêu cầu)

        req.getRequestDispatcher("home.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
