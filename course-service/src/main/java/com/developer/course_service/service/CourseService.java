package com.developer.course_service.service;

import com.developer.course_service.dao.CourseDao;
import com.developer.course_service.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Optional<Course>> getCourseById(Integer id) {
        Optional<Course> findCourse=courseDao.findById(id);
        if(findCourse.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findCourse,HttpStatus.OK);
    }

    public ResponseEntity<String> addCourse(Course course) {
        Optional<Course> findCourse=courseDao.findByCourseTitle(course.getCourseTitle());
        if(findCourse.isPresent()){
            return new ResponseEntity<>("Course is Already exists",HttpStatus.BAD_REQUEST);
        }
        else {
            courseDao.save(course);
            return new ResponseEntity<>("Added new Course",HttpStatus.CREATED);
        }
    }

    public ResponseEntity<List<Course>> getCoursesByCategory(String category) {
        List<Course> findCourses=courseDao.findByCourseCategory(category);
        if(findCourses.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findCourses,HttpStatus.OK);
    }

    public ResponseEntity<String> updateCourse(Integer id, Course course) {
        Optional<Course> findCourse=courseDao.findById(id);
        if(findCourse.isPresent()){
            courseDao.save(course);
            return new ResponseEntity<>("Updated the course.",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Course not found.",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteCourse(Integer id) {
        Optional<Course> courseOptional=courseDao.findById(id);
        if(courseOptional.isPresent()){
            courseDao.deleteById(id);
            return new ResponseEntity<>("Deleted the course",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No course",HttpStatus.NOT_FOUND);
        }
    }
}
