package com.mystore.codeshop.repository;

import com.mystore.codeshop.entity.DigitalCode;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DigitalCodeRepository extends JpaRepository<DigitalCode, Long> {
    List<DigitalCode> findByProductIdAndSoldFalse(Long productId, Pageable pageable);
}

