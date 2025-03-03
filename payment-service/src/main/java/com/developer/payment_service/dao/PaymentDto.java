package com.developer.payment_service.dao;

import com.developer.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDto extends JpaRepository<Payment,Long> {
    List<Payment> findPaymentsByStudentId(Long id);
}

