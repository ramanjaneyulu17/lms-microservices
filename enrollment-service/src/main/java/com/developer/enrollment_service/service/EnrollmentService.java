package com.developer.enrollment_service.service;

import com.developer.enrollment_service.client.CourseClient;
import com.developer.enrollment_service.client.UserClient;
import com.developer.enrollment_service.model.Enrollment;
import com.developer.enrollment_service.repository.EnrollmentDao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentDao enrollmentDao;

    @Autowired
    CourseClient courseClient;

    @Autowired
    UserClient userClient;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentDao.findById(id);
    }

    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentDao.findEnrollmentsByUserId(userId);
    }

    public ResponseEntity<String> enrollUser(Enrollment enrollment) {
        try {
            String course = courseClient.getCourseById(Math.toIntExact(enrollment.getCourseId()));
            String user = userClient.getUserById(Math.toIntExact(enrollment.getUserId()));
            enrollment.setEnrollmentDate(LocalDateTime.now());
            enrollmentDao.save(enrollment);
            return new ResponseEntity<>("Enrolled Successfully",HttpStatus.OK);
        }catch (FeignException.NotFound e){
            return new ResponseEntity<>("User or Course not found",HttpStatus.NOT_FOUND);
        }
    }

    public String deleteEnrollment(Long id) {
        enrollmentDao.deleteById(id);
        return "deleted";
    }
}
