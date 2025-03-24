package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;

public class DepartmentMapper {
    //Create a static method to convert department jpa entity to department Dto
    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    //Convert department dto to department entity
    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
