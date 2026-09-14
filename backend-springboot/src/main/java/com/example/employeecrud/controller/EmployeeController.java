package com.example.employeecrud.controller;

import java.util.*;

import com.example.employeecrud.dto.ApiResponse;
import com.example.employeecrud.dto.EmployeeDTO;
import com.example.employeecrud.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public ResponseEntity<ApiResponse> getEmployee() {

        List<EmployeeDTO> employees = employeeService.getEmployee();

        return ResponseEntity.ok(
                new ApiResponse(
                        "Employees fetched successfully",
                        employees
                )
        );
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<ApiResponse> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        System.out.println("save method " + employeeDTO);

        employeeService.saveEmployee(employeeDTO);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Employee saved successfully",
                        employeeDTO
                )
        );
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Employee deleted successfully",
                        "Employee ID: " + id
                )
        );
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String id, @Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeService.updateEmployee(id, employeeDTO);

        employeeDTO.setId(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Employee updated successfully",
                        employeeDTO
                )
        );
    }
}