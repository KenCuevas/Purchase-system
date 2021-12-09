package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.services.impl.AccountingIntegrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController {
    private final AccountingIntegrateService integrateService;

    @Autowired
    public IntegrationController(AccountingIntegrateService integrateService) {
        this.integrateService = integrateService;
    }

    @GetMapping("/")
    public String getData(){
        return integrateService.consumeAPI();
    }
}