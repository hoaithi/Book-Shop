package com.hoaithidev.bookstore.controller;

import com.hoaithidev.bookstore.dao.AuthorDAO;
import com.hoaithidev.bookstore.dao.BookDAO;
import com.hoaithidev.bookstore.dao.CategoryDAO;
import com.hoaithidev.bookstore.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.math.BigDecimal;
import java.util.UUID;

public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private CategoryDAO categoryDAO;
    @Override
    public void init(){
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                editBook(req, resp);
                break;
            case "delete":
                deleteBook(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add", "edit":
                try {
                    addBook(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookDAO.deleteBook(id);
        req.setAttribute("books", bookDAO.getBooks());
        req.getRequestDispatcher("views/admin/manage-books.jsp").forward(req, resp);
    }


    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("categories",categoryDAO.getCategories());
        req.setAttribute("authors", authorDAO.getAuthors());
        req.setAttribute("book", bookDAO.getBook(id));
        req.getRequestDispatcher("/views/book/edit-book.jsp").forward(req,resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories",categoryDAO.getCategories());
        req.setAttribute("authors", authorDAO.getAuthors());
        req.getRequestDispatcher(req.getContextPath() + "/add-book.jsp").forward(req,resp);
    }
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String publisher = request.getParameter("publisher");
        int publishYear = Integer.parseInt(request.getParameter("publishYear"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String language = request.getParameter("language");
        String format = request.getParameter("format");
        UUID authorId = UUID.fromString(request.getParameter("authorId"));
        UUID categoryId = UUID.fromString(request.getParameter("categoryId"));
        String imageUrl = request.getParameter("imageUrl");

//        // Handle uploaded file
//        Part imagePart = request.getPart("image");
//        String fileName = imagePart.getSubmittedFileName();
//        // Tạo file tạm
//        File tempFile = File.createTempFile("upload_", fileName);
//        try (InputStream input = imagePart.getInputStream();
//             FileOutputStream fos = new FileOutputStream(tempFile)) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = input.read(buffer)) != -1) {
//                fos.write(buffer, 0, bytesRead);
//            }
//        }
//
//        // Upload file lên Cloudinary
//        String imageUrl = CloudinaryUtil.uploadFile(tempFile);
//
//        // Xoá file tạm sau khi upload thành công (optional)
//        tempFile.delete();

        // 5. Create Book object
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setPrice(price);
        book.setPublisher(publisher);
        book.setPublishYear(publishYear);
        book.setStockQuantity(stockQuantity);
        book.setLanguage(language);
        book.setFormat(format);
        book.setAuthorId(authorId);
        book.setCategoryId(categoryId);
        book.setImage(imageUrl);

        // 6. Save to DB
        bookDAO.create(book);
        HttpSession session = request.getSession();
        session.setAttribute("books", bookDAO.getBooks());
        response.sendRedirect("views/admin/manage-books.jsp");


    }

}
