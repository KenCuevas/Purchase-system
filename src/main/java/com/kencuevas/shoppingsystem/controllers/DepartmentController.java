package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.DepartmentDTO;
import com.kencuevas.shoppingsystem.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/department")
    public ResponseEntity<DepartmentDTO>createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO), HttpStatus.CREATED);
    }
    @GetMapping("/all/department")
    public List<DepartmentDTO> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    @GetMapping("/search/department/{id}")
    public ResponseEntity<DepartmentDTO>getDepartmentById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/department/{id}")
    public ResponseEntity<DepartmentDTO>updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable(value = "id") long id){
        DepartmentDTO departmentResponse = departmentService.updateDepartment(departmentDTO, id);

        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/department/{id}")
    public ResponseEntity<String>deleteDepartment(@PathVariable(name = "id") long id){
        departmentService.deleteDepartment(id);

        return new ResponseEntity<>("Department entity delete successfully", HttpStatus.OK);
    }
}
