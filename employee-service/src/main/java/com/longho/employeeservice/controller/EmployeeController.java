package com.longho.employeeservice.controller;

import com.longho.employeeservice.dto.EmployeeDto;
import com.longho.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    // Build Save employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{employee-id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employee-id") Long id) {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }
}
