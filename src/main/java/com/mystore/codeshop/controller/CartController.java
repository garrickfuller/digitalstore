package com.mystore.codeshop.controller;

import com.mystore.codeshop.entity.Cart;
import com.mystore.codeshop.entity.User;
import com.mystore.codeshop.service.CartService;
import com.mystore.codeshop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    /**
     * Helper method to get the currently authenticated user.
     */
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    /**
     * GET /api/cart - Retrieve the current user's cart.
     */
    @GetMapping
    public ResponseEntity<Cart> getCart() {
        User user = getCurrentUser();
        Cart cart = cartService.getCartByUser(user);
        return ResponseEntity.ok(cart);
    }

    /**
     * POST /api/cart/add?productId=1&quantity=2 - Add an item to the cart.
     */
    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestParam Long productId, @RequestParam int quantity) {
        User user = getCurrentUser();
        Cart cart = cartService.addItemToCart(user, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    /**
     * PUT /api/cart/update?cartItemId=1&quantity=3 - Update the quantity of an item.
     */
    @PutMapping("/update")
    public ResponseEntity<Cart> updateItemQuantity(@RequestParam Long cartItemId, @RequestParam int quantity) {
        User user = getCurrentUser();
        Cart cart = cartService.updateItemQuantity(user, cartItemId, quantity);
        return ResponseEntity.ok(cart);
    }

    /**
     * DELETE /api/cart/remove?cartItemId=1 - Remove an item from the cart.
     */
    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeItem(@RequestParam Long cartItemId) {
        User user = getCurrentUser();
        Cart cart = cartService.removeItemFromCart(user, cartItemId);
        return ResponseEntity.ok(cart);
    }

    /**
     * DELETE /api/cart/clear - Clear the entire cart.
     */
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        User user = getCurrentUser();
        cartService.clearCart(user);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
