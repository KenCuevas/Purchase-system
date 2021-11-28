package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartment();
    DepartmentDTO getDepartmentById(long id);
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, long id);
    void deleteDepartment(long id);
}
