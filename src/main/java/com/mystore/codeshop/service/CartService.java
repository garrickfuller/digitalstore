package com.mystore.codeshop.service;

import com.mystore.codeshop.entity.Cart;
import com.mystore.codeshop.entity.CartItem;
import com.mystore.codeshop.entity.Product;
import com.mystore.codeshop.entity.User;
import com.mystore.codeshop.repository.CartRepository;
import com.mystore.codeshop.repository.ProductRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    /**
     * Retrieve the cart for the given user. If no cart exists, create a new one.
     */
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart(user);
            return cartRepository.save(newCart);
        });
    }

    /**
     * Add a product to the user's cart with the specified quantity.
     */
    @Transactional
    public Cart addItemToCart(User user, Long productId, int quantity) {
        Cart cart = getCartByUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if this product is already in the cart; if so, update its quantity.
        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(cart, product, quantity);
            cart.addCartItem(cartItem);
        }
        return cartRepository.save(cart);
    }

    /**
     * Update the quantity of a cart item.
     */
    @Transactional
    public Cart updateItemQuantity(User user, Long cartItemId, int newQuantity) {
        Cart cart = getCartByUser(user);
        for (CartItem item : cart.getCartItems()) {
            if (item.getId().equals(cartItemId)) {
                if (newQuantity <= 0) {
                    // Remove the item if the new quantity is zero or less
                    cart.removeCartItem(item);
                } else {
                    item.setQuantity(newQuantity);
                }
                break;
            }
        }
        return cartRepository.save(cart);
    }

    /**
     * Remove an item from the user's cart.
     */
    @Transactional
    public Cart removeItemFromCart(User user, Long cartItemId) {
        Cart cart = getCartByUser(user);
        cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
        return cartRepository.save(cart);
    }

    /**
     * Clear all items from the user's cart.
     */
    @Transactional
    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
