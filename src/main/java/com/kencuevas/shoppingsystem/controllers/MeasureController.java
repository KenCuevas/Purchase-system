package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.UnitMeasureDTO;
import com.kencuevas.shoppingsystem.services.UnitMeasureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MeasureController {
    private UnitMeasureService measureService;

    public MeasureController(UnitMeasureService measureService) {
        this.measureService = measureService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    // This function allows the user to add new unit measure to the database
    @PostMapping("/add/measure")
    public ResponseEntity<UnitMeasureDTO>createMeasure(@RequestBody UnitMeasureDTO measureDTO){
        return new ResponseEntity<>(measureService.createMeasure(measureDTO), HttpStatus.CREATED);
    }

    @GetMapping("/search/measure")
    public List<UnitMeasureDTO>getAllMeasure(){
        return measureService.getAllMeasure();
    }

    // This function allows us to search for a record by ID of the measure stored in the database
    @GetMapping("/search/measure/{id}")
    public ResponseEntity<UnitMeasureDTO>getMeasureById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(measureService.getMeasureById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    //This function allows us to update a unit measure that we have stored in our database
    @PutMapping("/update/measure/{id}")
    public ResponseEntity<UnitMeasureDTO> updateMeasure(@RequestBody UnitMeasureDTO measureDTO, @PathVariable (name = "id")long id){
        UnitMeasureDTO measureResponse = measureService.updateMeasure(measureDTO, id);

        return new ResponseEntity<>(measureResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    //This function deletes the record corresponding to the id we passed in the endpoint
    @DeleteMapping("/delete/measure/{id}")
    public ResponseEntity<String>deleteMeasure(@PathVariable(name = "id") long id){
        measureService.deleteMeasureById(id);

        return new ResponseEntity<>("Unit measure entity delete successfully", HttpStatus.OK);
    }

}
