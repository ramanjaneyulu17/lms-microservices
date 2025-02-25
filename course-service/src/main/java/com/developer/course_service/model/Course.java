package com.developer.course_service.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseTitle;
    private String courseDescription;
    private String courseInstructor;
    private Double coursePrice;
    private String courseCategory;

}
