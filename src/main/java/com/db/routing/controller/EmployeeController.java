package com.db.routing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.routing.dto.EmployeeDto;
import com.db.routing.service.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto.EmployeeResponse> saveEmployee(@RequestBody EmployeeDto.EmployeeRequest entity) {
        return ResponseEntity.ok(employeeService.saveEmployee(entity));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto.EmployeeResponse>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto.EmployeeResponse> getEmployeeById(@PathVariable String id) {
        return null;
    }
    
    @GetMapping("/list")
    public String getEmployeeHireDate(@RequestParam LocalDateTime hireDate) {
        return new String();
    }
    
}
