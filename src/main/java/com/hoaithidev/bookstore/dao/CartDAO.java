package com.hoaithidev.bookstore.dao;

import com.hoaithidev.bookstore.dao.interfacedao.ICartDAO;
import com.hoaithidev.bookstore.model.Cart;
import com.hoaithidev.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.UUID;

public class CartDAO implements ICartDAO {

    /**
     * @param userID
     * @return
     */
    @Override
    public Cart findByUserId(UUID userID) {
        String sql = "select * from cart where user_id = ?";
        Cart cart = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setObject(1, userID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                cart = new Cart();
                cart.setUserId(userID);
                cart.setId(UUID.fromString(rs.getString("id")));
            }
            return cart;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    /**
     * @param cart
     */
    @Override
    public void insert(Cart cart) {
        String sql = "insert into cart(user_id) values(?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setObject(1, cart.getUserId());
            stmt.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
