package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.DepartmentDTO;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Department;
import com.kencuevas.shoppingsystem.repositories.DepartmentRepository;
import com.kencuevas.shoppingsystem.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository repository;
    private ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        //Convert DTO to entity
        Department department = mapToEntity(departmentDTO);
        Department newDepartment = repository.save(department);

        // Convert Entity to DTO
        DepartmentDTO departmentReponse = mapToDTO(newDepartment);
        return departmentReponse;
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departmentList = repository.findAll();
        return departmentList.stream().map(department -> mapToDTO(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(long id) {
       Department department = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id));
       return mapToDTO(department);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, long id) {
        // First we must obtain the id of the department, i.e., search for the department by ID
        Department department = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id));

        department.setName(departmentDTO.getName());
        department.setStatus(departmentDTO.isStatus());

        Department updateDepartment = repository.save(department);

        return mapToDTO(updateDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        Department department = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id));
        repository.delete(department);
    }

    // Convert entity into DTO
    private DepartmentDTO mapToDTO(Department department){
        DepartmentDTO departmentDTO = mapper.map(department, DepartmentDTO.class);

        return departmentDTO;
    }
    // Convert DTO to entity
    private Department mapToEntity(DepartmentDTO departmentDTO){
        Department department = mapper.map(departmentDTO, Department.class);

        return department;
    }
}
