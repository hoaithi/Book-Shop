package com.hoaithidev.bookstore.controller;

import com.hoaithidev.bookstore.dao.UserDAO;
import com.hoaithidev.bookstore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RegisterController extends HttpServlet {
    private final UserDAO userDAO;
    private static final long serialVersionUID = 1L;
    public RegisterController() {
        super();
        this.userDAO = new UserDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath()+"/register.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        userDAO.create(new User(username, password, email, phone, address));

        HttpSession session = request.getSession();
        session.setAttribute("flash","successful registration");
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
