package com.developer.enrollment_service.repository;

import com.developer.enrollment_service.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentDao extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findEnrollmentsByUserId(Long userId);
}

