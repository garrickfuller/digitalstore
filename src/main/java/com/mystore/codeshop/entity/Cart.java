package com.mystore.codeshop.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each cart is associated with one user (assumes one active cart per user)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // A cart contains one or more cart items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // Constructors
    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    // Convenience method to add an item
    public void addCartItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
    }
    
    // Convenience method to remove an item
    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
    }
}
