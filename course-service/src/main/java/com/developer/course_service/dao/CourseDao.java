package com.developer.course_service.dao;

import com.developer.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseTitle(String courseTitle);

    List<Course> findByCourseCategory(String category);
}


