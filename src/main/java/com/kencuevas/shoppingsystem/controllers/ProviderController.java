package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;
import com.kencuevas.shoppingsystem.services.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class ProviderController {
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // This function allows the user to add new suppliers to the database
    @PostMapping("/add/provider")
    public ResponseEntity<ProviderDTO> createProvider(@RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.createProvider(providerDTO), HttpStatus.CREATED);
    }
    // This function allows us to obtain all the suppliers that we have stored in the database
    @GetMapping("/search/provider")
    public List<ProviderDTO>getAllProviders(){
        return providerService.getAllProvider();
    }
    // This function allows us to search for a record by ID of the suppliers stored in the database.
    @GetMapping("/search/provider/{id}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(providerService.getProviderById(id));
    }
    // This function allows us to update a supplier that we have stored in our database
    @PutMapping("/update/provider/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(@RequestBody  ProviderDTO providerDTO, @PathVariable(name = "id") long id){
        ProviderDTO providerResponse =  providerService.updateProvider(providerDTO, id);

        return new ResponseEntity<>(providerResponse, HttpStatus.OK);
    }
    // This function deletes the record corresponding to the id we passed in the endpoint.
    @DeleteMapping("/delete/provider/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable(name = "id") long id){
        providerService.deleteProviderById(id);

        return new ResponseEntity<>("Provider entity delete successfully.", HttpStatus.OK);
    }
}
