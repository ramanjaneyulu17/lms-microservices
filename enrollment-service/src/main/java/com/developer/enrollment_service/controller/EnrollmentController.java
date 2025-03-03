package com.developer.enrollment_service.controller;

import com.developer.enrollment_service.model.Enrollment;
import com.developer.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
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

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable Long studentId){
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    @PostMapping("/enrollStudent")
    public Enrollment enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId){
        return enrollmentService.enrollStudent(studentId,courseId);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public String deleteEnrollment(@PathVariable Long id){
        return enrollmentService.deleteEnrollment(id);
    }
}
