package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;
import com.kencuevas.shoppingsystem.dto.ProviderResponse;
import com.kencuevas.shoppingsystem.services.ProviderService;
import com.kencuevas.shoppingsystem.utils.AppContants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class ProviderController {
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    // This function allows the user to add new suppliers to the database
    @PostMapping("/add/provider")
    public ResponseEntity<ProviderDTO> createProvider(@RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.createProvider(providerDTO), HttpStatus.CREATED);
    }
    // This function allows us to obtain all the suppliers that we have stored in the database
    @GetMapping("/search/provider")
    public ProviderResponse getAllProviders(
            @RequestParam(value = "pageNumber", defaultValue = AppContants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppContants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppContants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppContants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return providerService.getAllProvider(pageNumber, pageSize,sortBy, sortDir);
    }
    @GetMapping("/all/providers")
    public List<ProviderDTO>getAllSuppliers(){
        return providerService.getAllSuppliers();
    }
    // This function allows us to search for a record by ID of the suppliers stored in the database.
    @GetMapping("/search/provider/{id}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(providerService.getProviderById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    // This function allows us to update a supplier that we have stored in our database
    @PutMapping("/update/provider/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(@RequestBody  ProviderDTO providerDTO, @PathVariable(name = "id") long id){
        ProviderDTO providerResponse =  providerService.updateProvider(providerDTO, id);

        return new ResponseEntity<>(providerResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    // This function deletes the record corresponding to the id we passed in the endpoint.
    @DeleteMapping("/delete/provider/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable(name = "id") long id){
        providerService.deleteProviderById(id);

        return new ResponseEntity<>("Provider entity delete successfully.", HttpStatus.OK);
    }
}
