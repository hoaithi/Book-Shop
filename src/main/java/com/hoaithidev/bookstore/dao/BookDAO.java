package com.hoaithidev.bookstore.dao;

import com.hoaithidev.bookstore.dao.interfacedao.IBookDAO;
import com.hoaithidev.bookstore.model.Book;
import com.hoaithidev.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDAO implements IBookDAO {
    public BookDAO() {}

    @Override
    public boolean create(Book book) {
        String sql = "INSERT INTO books (title, author_id, category_id, price, image, description, " +
                "publisher, publish_year, stock_quantity, language, format) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setObject(2, book.getAuthorId());
            ps.setObject(3, book.getCategoryId());
            ps.setBigDecimal(4, book.getPrice());
            ps.setString(5, book.getImage());
            ps.setString(6, book.getDescription());
            ps.setString(7, book.getPublisher());
            ps.setInt(8, book.getPublishYear());
            ps.setInt(9, book.getStockQuantity());
            ps.setString(10, book.getLanguage());
            ps.setString(11, book.getFormat());
            return ps.executeUpdate()>1;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting book into database", e);
        }
    }

    @Override
    public List<Book> getBooks() {
        String sql = "select b.id, b. title, b.price, b.description, b.image , a.name author_name, \n" +
                "c.name category_name, b.created_at\n" +
                "from Books b \n" +
                "left join authors a on b.author_id = a.id\n" +
                "left join categories c on b.category_id = c.id\n" +
                "order by created_at desc;";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            List<Book> books = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setId(UUID.fromString(rs.getString("id")));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setAuthorName(rs.getString("author_name"));
                book.setCategoryName(rs.getString("category_name"));
                book.setImage(rs.getString("image"));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBook(String id) {

        String sql = "select * from books where id = ?::uuid";
        try(Connection con = DBUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return fromResultSetToBook(rs);
            }
           return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String id) {
        String sql = "delete from books where id = ?::uuid";
        try(Connection con = DBUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Book fromResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(UUID.fromString(rs.getString("id")));
        book.setTitle(rs.getString("title"));
        book.setDescription(rs.getString("description"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setAuthorId(UUID.fromString(rs.getString("author_id")));
        book.setCategoryId(UUID.fromString(rs.getString("category_id")));
        book.setPublisher(rs.getString("publisher"));
        book.setPublishYear(rs.getInt("publish_year"));
        book.setStockQuantity(rs.getInt("stock_quantity"));
        book.setLanguage(rs.getString("language"));
        book.setFormat(rs.getString("format"));
        book.setImage(rs.getString("image"));
        return book;

    }


}
