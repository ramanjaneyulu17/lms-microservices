package com.developer.course_service.service;

import com.developer.course_service.dao.CourseDao;
import com.developer.course_service.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseDao.findById(id);
    }

    public String addCourse(Course course) {
        Optional<Course> course1=courseDao.findByCourseTitle(course.getCourseTitle());
        if(course1.isPresent()){
            return "Course is Already exists";
        }
        else {
            courseDao.save(course);
            return "Added new Course";
        }
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseDao.findByCourseCategory(category);
    }

    public String updateCourse(Integer id, Course course) {
        Optional<Course> course1=courseDao.findById(id);
        if(course1.isPresent()){
            courseDao.save(course);
            return "Updated the course.";
        }else{
            return "Course not found.";
        }
    }

    public String deleteCourse(Integer id) {
        Optional<Course> courseOptional=courseDao.findById(id);
        if(courseOptional.isPresent()){
            courseDao.deleteById(id);
            return "Deleted the course";
        }else{
            return "No course";
        }
    }
}
