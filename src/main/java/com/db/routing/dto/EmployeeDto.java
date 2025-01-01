package com.db.routing.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public interface EmployeeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    class EmployeeRequest{
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String jobId;
        private String salary;
        private LocalDate hireDate;
        private LocalDateTime joiningDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private ZonedDateTime terminatedDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private OffsetDateTime offsetDateTime;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EmployeeResponse{
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String jobId;
        private String salary;
        private LocalDate hireDate;
        private LocalDateTime joiningDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private ZonedDateTime terminatedDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private OffsetDateTime offsetDateTime;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }
}
