package com.hoaithidev.bookstore.dao;

import com.hoaithidev.bookstore.model.Category;
import com.hoaithidev.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryDAO {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from categories";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Category category = new Category();
                category.setId(UUID.fromString(rs.getString("id")));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
