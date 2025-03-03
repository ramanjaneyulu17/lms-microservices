package com.developer.payment_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long studentId;
    private Long courseId;
    private Double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;
}
