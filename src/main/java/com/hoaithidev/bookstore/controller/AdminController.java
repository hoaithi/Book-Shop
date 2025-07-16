package com.hoaithidev.bookstore.controller;

import com.hoaithidev.bookstore.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private CategoryDAO categoryDAO;
    private UserDAO userDAO;
    private OrderDAO orderDAO;
    public AdminController() {
        this.authorDAO = new AuthorDAO();
        this.bookDAO = new BookDAO();
        this.categoryDAO = new CategoryDAO();
        this.userDAO = new UserDAO();
        this.orderDAO = new OrderDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "book":
                showBook(request, response);
                break;
            case "author":
                showAuthor(request, response);
                break;
            case "category":
                showCategory(request,response);
                break;
            case "user":
                showUser(request,response);
                break;
            case "order":
                showOrder(request,response);
                break;
        }
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showUser(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryDAO.getCategories());
        request.getRequestDispatcher("/views/admin/manage-categories.jsp").forward(request, response);
    }

    private void showAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("authors", authorDAO.getAuthors());
        request.getRequestDispatcher("/views/admin/manage-authors.jsp").forward(request, response);
    }

    private void showBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", bookDAO.getBooks());
        request.getRequestDispatcher("/views/admin/manage-books.jsp").forward(request, response);
    }
}
