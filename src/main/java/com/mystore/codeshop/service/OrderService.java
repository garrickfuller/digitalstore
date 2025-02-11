package com.mystore.codeshop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mystore.codeshop.entity.DigitalCode;
import com.mystore.codeshop.entity.Order;
import com.mystore.codeshop.entity.User;
import com.mystore.codeshop.repository.DigitalCodeRepository;
import com.mystore.codeshop.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private DigitalCodeRepository digitalCodeRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Transactional
    public List<String> purchaseProduct(Long productId, int quantity, User user) {
        // Retrieve available digital codes for the product using paging
        List<DigitalCode> availableCodes = digitalCodeRepository
            .findByProductIdAndSoldFalse(productId, PageRequest.of(0, quantity));
        
        if (availableCodes.size() < quantity) {
            throw new RuntimeException("Not enough available digital codes.");
        }
        
        // Create a new order
        Order order = new Order(user, LocalDateTime.now());
        // (If needed, you can add additional order fields or logic here)
        
        // For each digital code, mark it as sold and add its code to a list
        List<String> purchasedCodes = new ArrayList<>();
        for (DigitalCode code : availableCodes) {
            code.setSold(true);
            code.setSoldAt(LocalDateTime.now());
            digitalCodeRepository.save(code);  // Persist the change
            
            // (Optionally, you can link the code to the order if you add such a relationship)
            purchasedCodes.add(code.getCode());
        }
        
        // Save the order record
        orderRepository.save(order);
        
        return purchasedCodes;
    }
}

