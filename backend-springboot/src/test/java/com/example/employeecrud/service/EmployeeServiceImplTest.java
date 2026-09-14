package com.example.employeecrud.service;

import com.example.employeecrud.dto.EmployeeDTO;
import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getEmployee_shouldReturnEmployeesSuccessfully() {

        Employee employee1 = new Employee();
        employee1.setId("1001");
        employee1.setName("Dileep");
        employee1.setEmail("dileep@gmail.com");
        employee1.setPhoneNumber("9876543210");
        employee1.setSalary(20000.0);

        Employee employee2 = new Employee();
        employee2.setId("1002");
        employee2.setName("John");
        employee2.setEmail("john@gmail.com");
        employee2.setPhoneNumber("9123456789");
        employee2.setSalary(30000.0);

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeDTO> result = employeeService.getEmployee();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("1001", result.get(0).getId());
        assertEquals("Dileep", result.get(0).getName());
        assertEquals("dileep@gmail.com", result.get(0).getEmail());
        assertEquals("9876543210", result.get(0).getPhoneNumber());
        assertEquals(20000.0, result.get(0).getSalary());

        assertEquals("1002", result.get(1).getId());
        assertEquals("John", result.get(1).getName());
        assertEquals("john@gmail.com", result.get(1).getEmail());
        assertEquals("9123456789", result.get(1).getPhoneNumber());
        assertEquals(30000.0, result.get(1).getSalary());

        verify(employeeRepository, times(1)).findAll();
    }


    @Test
    void saveEmployee_shouldSaveEmployeeSuccessfully() {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId("1001");
        employeeDTO.setName("Dileep");
        employeeDTO.setEmail("dileep@gmail.com");
        employeeDTO.setPhoneNumber("9876543210");
        employeeDTO.setSalary(20000.0);

        employeeService.saveEmployee(employeeDTO);

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_shouldDeleteEmployeeSuccessfully() {

        String employeeId = "1001";

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1))
                .deleteById(employeeId);
    }

    @Test
    void updateEmployee_shouldUpdateEmployeeSuccessfully() {

        String employeeId = "1001";

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setName("Dileep Updated");
        employeeDTO.setEmail("dileep.updated@gmail.com");
        employeeDTO.setPhoneNumber("9123456789");
        employeeDTO.setSalary(30000.0);

        employeeService.updateEmployee(employeeId, employeeDTO);

        verify(employeeRepository, times(1))
                .save(any(Employee.class));
    }

}