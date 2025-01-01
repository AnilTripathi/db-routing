package com.db.routing.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "job_id", nullable = false)
    private String jobId;
    @Column(name = "salary", nullable = false)
    private Double salary;
    @Column(name = "hire_date")
    private LocalDate hireDate;
    @Column(name = "joining_date")
    private LocalDateTime joiningDate;
    @Column(name = "terminated_date")
    private ZonedDateTime terminatedDate;
    @Column(name = "offset_date_time")
    private OffsetDateTime offsetDateTime;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


}
