package com.developer.payment_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "enrollment-service",url = "http://localhost:8083")
public interface EnrollmentClient {
    @GetMapping("/enrollments/student/{studentId}")
    String getEnrollmentsByStudentId(@PathVariable Long studentId);
}
