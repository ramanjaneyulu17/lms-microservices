package com.developer.payment_service.service;

import com.developer.payment_service.client.EnrollmentClient;
import com.developer.payment_service.dao.PaymentDto;
import com.developer.payment_service.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentDto paymentDto;

    @Autowired
    EnrollmentClient enrollmentClient;

    public List<Payment> getAllPayments() {
        return paymentDto.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentDto.findById(id);
    }

    public List<Payment> getPaymentsByStudentId(Long id) {
        return paymentDto.findPaymentsByStudentId(id);
    }

    public String makePayment(Long studId, Long courId, Double price) {
        String enrollment = enrollmentClient.getEnrollmentsByStudentId(studId);
        if(enrollment==null){
            return "No enrollments for the student";
        }else {
            Payment payment=new Payment();
            payment.setStudentId(studId);
            payment.setCourseId(courId);
            payment.setAmount(price);
            payment.setPaymentStatus("Success");
            payment.setPaymentDate(LocalDateTime.now());

            paymentDto.save(payment);

            return "Payment Success";
        }
    }

    public void deletePayement(Long id) {
        paymentDto.deleteById(id);
    }
}
