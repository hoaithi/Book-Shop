package com.hoaithidev.bookstore.model;

import java.util.UUID;

public class CartItem {
    private UUID id;
    private UUID cartId;
    private UUID bookId;
    private int quantity;
    private double price;
    public CartItem(){}
    public CartItem(UUID cartId, UUID bookId, int quantity, double price) {
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
