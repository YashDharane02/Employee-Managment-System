package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not exists with given id: " + departmentId)
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        System.out.println("Request received to update department with ID: " + departmentId);

        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not exists with given Id: " + departmentId)
        );

        System.out.println("Existing department found: " + department.getDepartmentName());

        if (updatedDepartment.getDepartmentName() != null) {
            department.setDepartmentName(updatedDepartment.getDepartmentName());
        }
        if (updatedDepartment.getDepartmentDescription() != null) {
            department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
        }

        System.out.println("Saving updated department...");

        Department savedDepartment = departmentRepository.save(department);

        System.out.println("Department updated successfully with ID: " + savedDepartment.getId());

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exists with given id: "+ departmentId)
        );

        departmentRepository.deleteById(departmentId);
    }

}
