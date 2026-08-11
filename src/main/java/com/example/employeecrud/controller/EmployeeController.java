package com.example.employeecrud.controller;

import java.util.*;
import com.example.employeecrud.dto.EmployeeDTO;
import com.example.employeecrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public List<EmployeeDTO> getEmployee(){
        return employeeService.getEmployee();
    }

    @PostMapping("/saveEmployee")
    public void saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        System.out.println("save method" + employeeDTO);
        employeeService.saveEmployee(employeeDTO);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/updateEmployee/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(id, employeeDTO);
    }

}
