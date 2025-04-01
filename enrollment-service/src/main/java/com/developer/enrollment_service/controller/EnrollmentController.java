package com.developer.enrollment_service.controller;

import com.developer.enrollment_service.model.Enrollment;
import com.developer.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/allEnrollments")
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable Long id){
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/userEnrollments/{userId}")
    public List<Enrollment> getEnrollmentsByUserId(@PathVariable Long userId){
        return enrollmentService.getEnrollmentsByUserId(userId);
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollUser(@RequestBody Enrollment enrollment){
        return enrollmentService.enrollUser(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public String deleteEnrollment(@PathVariable Long id){
        return enrollmentService.deleteEnrollment(id);
    }
}
