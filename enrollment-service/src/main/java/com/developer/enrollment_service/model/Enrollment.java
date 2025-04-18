package com.developer.enrollment_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="enrollments")
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long userId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
}
