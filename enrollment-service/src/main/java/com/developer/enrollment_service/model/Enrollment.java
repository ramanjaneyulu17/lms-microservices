package com.developer.enrollment_service.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="enrollments")
public class Enrollment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long studentId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
}
