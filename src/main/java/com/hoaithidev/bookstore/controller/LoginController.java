package com.hoaithidev.bookstore.controller;


import com.hoaithidev.bookstore.dao.CartDAO;
import com.hoaithidev.bookstore.dao.UserDAO;
import com.hoaithidev.bookstore.model.Cart;
import com.hoaithidev.bookstore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO;
    private final CartDAO cartDAO;
    public LoginController() {
        super();
        this.cartDAO = new CartDAO();
        this.userDAO = new UserDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user  = userDAO.findbyUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if(username.equals("admin")){
                response.sendRedirect(request.getContextPath() + "/views/admin/admin.jsp");
            }else{
                if(cartDAO.findByUserId(user.getId()) == null){
                    Cart cart = new Cart();
                    cart.setUserId(user.getId());
                    cartDAO.insert(cart);
                }
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }else {
            request.setAttribute("message", "Password Incorrect");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
