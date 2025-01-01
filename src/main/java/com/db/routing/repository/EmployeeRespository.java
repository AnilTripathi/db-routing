package com.db.routing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.routing.entity.Employee;

@Repository
public interface EmployeeRespository extends  JpaRepository<Employee, String> {
}
