package com.example.employeecrud.service;

import java.util.*;

import com.example.employeecrud.dto.EmployeeDTO;
import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getEmployee(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDTO> dtoList = new ArrayList<>();
        for(Employee employee : employeeList){
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDTO.getName());

        employeeRepository.save(employee);
    }

}
