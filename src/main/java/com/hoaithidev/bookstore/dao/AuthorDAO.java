package com.hoaithidev.bookstore.dao;

import com.hoaithidev.bookstore.model.Author;
import com.hoaithidev.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorDAO {
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "select * from authors";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setId(UUID.fromString(rs.getString("id")));
                author.setName(rs.getString("name"));
                authors.add(author);
            }
            return authors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
