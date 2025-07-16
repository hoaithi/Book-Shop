package com.hoaithidev.bookstore.dao.interfacedao;

import com.hoaithidev.bookstore.model.Cart;

import java.util.UUID;

public interface ICartDAO {
    Cart findByUserId(UUID userID);
    void insert(Cart cart);
}
