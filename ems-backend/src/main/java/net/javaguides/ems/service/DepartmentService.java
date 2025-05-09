package net.javaguides.ems.service;

import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);

    void deleteDepartment(Long departmentId);
}
