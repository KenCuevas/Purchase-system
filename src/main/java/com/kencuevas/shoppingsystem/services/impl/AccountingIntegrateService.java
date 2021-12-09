package com.kencuevas.shoppingsystem.services.impl;


import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;
import com.kencuevas.shoppingsystem.models.PurchaseOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountingIntegrateService {
    private final RestTemplate restTemplate;
    private ModelMapper mapper;

    @Autowired
    public AccountingIntegrateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public String consumeAPI(){
        return restTemplate.getForObject(
                "https://accountingaccountapi20211205021409.azurewebsites.net/api/AccountingSeat/List?Page=1&PageSize=50&Search=prueba&StartDate=2020-02-01&EndDate=2022-02-02",
                String.class);
    }
    //Convert entity into DTO
    private PurchaseOrderDTO mapToDTO(PurchaseOrder purchaseOrder){
        PurchaseOrderDTO purchaseOrderDTO = mapper.map(purchaseOrder, PurchaseOrderDTO.class);

        return  purchaseOrderDTO;
    }
    //Convert DTO to entity
    private PurchaseOrder mapToEntity(PurchaseOrderDTO purchaseOrderDTO){
        PurchaseOrder purchaseOrder = mapper.map(purchaseOrderDTO, PurchaseOrder.class);

        return  purchaseOrder;
    }
}
