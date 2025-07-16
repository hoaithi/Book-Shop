package com.hoaithidev.bookstore.dao.interfacedao;

import com.hoaithidev.bookstore.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {
    boolean create(Book book);

    List<Book> getBooks();

    Book getBook(String id);

    Book fromResultSetToBook(ResultSet rs) throws SQLException;

    void deleteBook(String id);
}
