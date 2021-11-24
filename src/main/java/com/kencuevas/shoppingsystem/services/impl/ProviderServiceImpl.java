package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;
import com.kencuevas.shoppingsystem.models.Provider;
import com.kencuevas.shoppingsystem.repositories.ProviderRepository;
import com.kencuevas.shoppingsystem.services.ProviderService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public ProviderDTO createProvider(ProviderDTO providerDTO) {

        // Convert DTO to entity
        Provider provider = mapToEntity(providerDTO);
        Provider newProvider = providerRepository.save(provider);

        //Convert Entity to DTO
        ProviderDTO providerResponse = mapToDTO(newProvider);
        return providerResponse;
    }

    @Override
    public List<ProviderDTO> getAllProvider() {
        List<Provider> providers = providerRepository.findAll();
        return providers.stream().map(provider -> mapToDTO(provider)).collect(Collectors.toList());
    }

    //Convert entity into DTO
    private ProviderDTO mapToDTO(Provider provider){
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(provider.getId());
        providerDTO.setCedula(provider.getCedula());
        providerDTO.setTradeName(provider.getTradeName());
        providerDTO.setStatus(provider.isStatus());
        return providerDTO;
    }
    //Convert DTO to entity
    private  Provider mapToEntity(ProviderDTO providerDTO){
        Provider provider = new Provider();
        provider.setCedula(providerDTO.getCedula());
        provider.setTradeName(providerDTO.getTradeName());
        provider.setStatus(providerDTO.isStatus());

        return provider;
    }
}
