package com.hoaithidev.bookstore.dao;

import com.hoaithidev.bookstore.dao.interfacedao.IUserDAO;
import com.hoaithidev.bookstore.model.User;
import com.hoaithidev.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class UserDAO implements IUserDAO {
    @Override
    public boolean create(User user) {
        String sql = "insert into users (username, password, email, phone, address, role) values (?,?,?,?,?,?)";
        try(Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User findbyUsername(String username) {
        String sql = "select * from users where username=?";
        try(Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery();){
                if (rs.next()) {
                    User user = new User();
                    user.setId(UUID.fromString(rs.getString("id")));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }catch (Exception e)  {
            throw new RuntimeException(e);
        }
        return null;
    }
}
