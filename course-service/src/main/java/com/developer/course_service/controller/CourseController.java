package com.developer.course_service.controller;

import com.developer.course_service.model.Course;
import com.developer.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/allCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Integer id){
        return courseService.getCourseById(id);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category){
        return courseService.getCoursesByCategory(category);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Integer id, @RequestBody Course course){
        return courseService.updateCourse(id,course);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id){
        return courseService.deleteCourse(id);
    }
}
