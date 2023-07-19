package com.longho.departmentservice.service.impl;

import com.longho.departmentservice.DepartmentNotFoundException;
import com.longho.departmentservice.dto.DepartmentDto;
import com.longho.departmentservice.entity.Department;
import com.longho.departmentservice.repository.DepartmentRepository;
import com.longho.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        try {
            // convert department dto to department jpa entity
            Department department = mapper.map(departmentDto, Department.class);
            Department savedDepartment = departmentRepository.save(department);
            DepartmentDto savedDepartmentDto = mapper.map(savedDepartment, DepartmentDto.class);
            return savedDepartmentDto;
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while saving the department.", ex);
        }
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        try {
            Department department = departmentRepository.findByDepartmentCode(departmentCode);
            if (department == null) {
                throw new DepartmentNotFoundException("Department not found with code: " + departmentCode);
            }
            DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
            return departmentDto;
        } catch (DepartmentNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while retrieving the department.", ex);
        }
    }
}
