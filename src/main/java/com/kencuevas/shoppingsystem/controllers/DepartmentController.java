package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.DepartmentDTO;
import com.kencuevas.shoppingsystem.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping("/add/department")
    public ResponseEntity<DepartmentDTO>createDepartment(@RequestBody DepartmentDTO departmentDTO){
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
    @PutMapping("/update/department/{id}")
    public ResponseEntity<DepartmentDTO>updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable(value = "id") long id){
        DepartmentDTO departmentResponse = departmentService.updateDepartment(departmentDTO, id);

        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/department/{id}")
    public ResponseEntity<String>deleteDepartment(@PathVariable(name = "id") long id){
        departmentService.deleteDepartment(id);

        return new ResponseEntity<>("Department entity delete successfully", HttpStatus.OK);
    }
}
