package com.hoaithidev.bookstore.listener;


import com.hoaithidev.bookstore.dao.BookDAO;
import com.hoaithidev.bookstore.dao.UserDAO;
import com.hoaithidev.bookstore.model.User;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (SQLException e) {
                System.err.println("Could not deregister driver: " + e);
            }
        }
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();
        if(userDAO.findbyUsername("admin")==null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPhone("0986342965");
            userDAO.create(admin);
            System.out.println("✅ Default admin account created: admin/admin");
        }
        sce.getServletContext().setAttribute("books", bookDAO.getBooks());

    }



}
