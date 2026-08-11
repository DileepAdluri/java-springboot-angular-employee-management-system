package com.example.employeecrud.service;

import java.util.*;
import com.example.employeecrud.dto.EmployeeDTO;

public interface EmployeeService {
    public List<EmployeeDTO> getEmployee();
    public void saveEmployee(EmployeeDTO employeeDTO);
    public void deleteEmployee(String id);
    public void updateEmployee(String id, EmployeeDTO employeeDTO);
}
