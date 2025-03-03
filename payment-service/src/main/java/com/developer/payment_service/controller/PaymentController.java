package com.developer.payment_service.controller;

import com.developer.payment_service.model.Payment;
import com.developer.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/students/{studentId}")
    public List<Payment> getPaymentsByStudentId(@PathVariable("studentId") Long id){
        return paymentService.getPaymentsByStudentId(id);
    }

    @PostMapping("makePayment")
    public String makePayment(@RequestParam Long studId,@RequestParam Long courId, @RequestParam Double price){
        return paymentService.makePayment(studId,courId,price);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id){
         paymentService.deletePayment(id);
    }
}
