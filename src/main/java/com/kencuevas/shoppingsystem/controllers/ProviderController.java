package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;
import com.kencuevas.shoppingsystem.services.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProviderController {
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    //Create new provider
    @PostMapping("/create/provider")
    public ResponseEntity<ProviderDTO> createProvider(@RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.createProvider(providerDTO), HttpStatus.CREATED);
    }
    // Get all provider
    @GetMapping("/search/provider")
    public List<ProviderDTO>getAllProviders(){
        return providerService.getAllProvider();
    }
}
