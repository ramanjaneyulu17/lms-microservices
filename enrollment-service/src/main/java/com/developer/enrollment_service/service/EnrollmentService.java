package com.developer.enrollment_service.service;

import com.developer.enrollment_service.client.CourseClient;
import com.developer.enrollment_service.model.Enrollment;
import com.developer.enrollment_service.repository.EnrollmentDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentDao.findById(id);
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentDao.findEnrollmentsByStudentId(studentId);
    }

    public Enrollment enrollStudent(Long studentId, Long courseId) {
        String course=courseClient.getCourseById(Math.toIntExact(courseId));
        if(course==null){
            throw new RuntimeException("Course not found");
        }
        Enrollment enrollment=new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        return enrollmentDao.save(enrollment);
    }

    public String deleteEnrollment(Long id) {
        enrollmentDao.deleteById(id);
        return "deleted";
    }
}
