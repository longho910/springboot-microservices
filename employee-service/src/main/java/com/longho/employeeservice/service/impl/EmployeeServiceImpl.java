package com.longho.employeeservice.service.impl;

import com.longho.employeeservice.dto.EmployeeDto;
import com.longho.employeeservice.entity.Employee;
import com.longho.employeeservice.exception.ResourceNotFoundException;
import com.longho.employeeservice.repository.EmployeeRepository;
import com.longho.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + id)
        );
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
