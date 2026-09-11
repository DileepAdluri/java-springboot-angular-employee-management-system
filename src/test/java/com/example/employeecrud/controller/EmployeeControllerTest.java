package com.example.employeecrud.controller;

import com.example.employeecrud.dto.EmployeeDTO;
import com.example.employeecrud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEmployee_shouldReturnEmployeesSuccessfully() throws Exception {

        EmployeeDTO employee = new EmployeeDTO();

        employee.setId("1001");
        employee.setName("Dileep");
        employee.setEmail("dileep@gmail.com");
        employee.setPhoneNumber("9876543210");
        employee.setSalary(20000.0);

        when(employeeService.getEmployee())
                .thenReturn(Arrays.asList(employee));

        mockMvc.perform(
                        get("/employee/getEmployee")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Employees fetched successfully"))
                .andExpect(jsonPath("$.data[0].id")
                        .value("1001"))
                .andExpect(jsonPath("$.data[0].name")
                        .value("Dileep"))
                .andExpect(jsonPath("$.data[0].email")
                        .value("dileep@gmail.com"))
                .andExpect(jsonPath("$.data[0].phoneNumber")
                        .value("9876543210"))
                .andExpect(jsonPath("$.data[0].salary")
                        .value(20000.0));

        verify(employeeService, times(1)).getEmployee();
    }

    @Test
    void saveEmployee_shouldSaveEmployeeSuccessfully() throws Exception {

        EmployeeDTO employee = new EmployeeDTO();

        employee.setId("1001");
        employee.setName("Dileep");
        employee.setEmail("dileep@gmail.com");
        employee.setPhoneNumber("9876543210");
        employee.setSalary(20000.0);

        doNothing()
                .when(employeeService)
                .saveEmployee(any(EmployeeDTO.class));

        mockMvc.perform(
                        post("/employee/saveEmployee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(employee))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Employee saved successfully"))
                .andExpect(jsonPath("$.data.id")
                        .value("1001"))
                .andExpect(jsonPath("$.data.name")
                        .value("Dileep"))
                .andExpect(jsonPath("$.data.email")
                        .value("dileep@gmail.com"))
                .andExpect(jsonPath("$.data.phoneNumber")
                        .value("9876543210"))
                .andExpect(jsonPath("$.data.salary")
                        .value(20000.0));

        verify(employeeService, times(1))
                .saveEmployee(any(EmployeeDTO.class));
    }

    @Test
    void deleteEmployee_shouldDeleteEmployeeSuccessfully() throws Exception {

        doNothing()
                .when(employeeService)
                .deleteEmployee("1001");

        mockMvc.perform(
                        delete("/employee/deleteEmployee/1001")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Employee deleted successfully"))
                .andExpect(jsonPath("$.data")
                        .value("Employee ID: 1001"));

        verify(employeeService, times(1))
                .deleteEmployee("1001");
    }

    @Test
    void updateEmployee_shouldUpdateEmployeeSuccessfully() throws Exception {

        EmployeeDTO employee = new EmployeeDTO();

        employee.setName("Dileep Updated");
        employee.setEmail("dileep.updated@gmail.com");
        employee.setPhoneNumber("9123456789");
        employee.setSalary(30000.0);

        doNothing()
                .when(employeeService)
                .updateEmployee(
                        anyString(),
                        any(EmployeeDTO.class)
                );

        mockMvc.perform(
                        put("/employee/updateEmployee/1001")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(employee))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Employee updated successfully"))
                .andExpect(jsonPath("$.data.id")
                        .value("1001"))
                .andExpect(jsonPath("$.data.name")
                        .value("Dileep Updated"))
                .andExpect(jsonPath("$.data.email")
                        .value("dileep.updated@gmail.com"))
                .andExpect(jsonPath("$.data.phoneNumber")
                        .value("9123456789"))
                .andExpect(jsonPath("$.data.salary")
                        .value(30000.0));

        verify(employeeService, times(1))
                .updateEmployee(
                        eq("1001"),
                        any(EmployeeDTO.class)
                );
    }

}