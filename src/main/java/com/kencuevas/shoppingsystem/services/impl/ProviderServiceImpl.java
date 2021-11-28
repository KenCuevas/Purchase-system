package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;
import com.kencuevas.shoppingsystem.dto.ProviderResponse;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Provider;
import com.kencuevas.shoppingsystem.repositories.ProviderRepository;
import com.kencuevas.shoppingsystem.services.ProviderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
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
    public ProviderResponse getAllProvider(int pageNumber, int pageSize, String sortBy,String sortDir) {

        //Sort in ascending and descending order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable instance
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Provider> providers = providerRepository.findAll(pageable);

        //Get content for page object
        List<Provider>listOfProviders = providers.getContent();

        List<ProviderDTO> content =  listOfProviders.stream().map(provider -> mapToDTO(provider)).collect(Collectors.toList());


        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setContent(content);
        providerResponse.setPageNumber(providers.getNumber());
        providerResponse.setPageSize(providers.getSize());
        providerResponse.setTotalElements(providers.getTotalElements());
        providerResponse.setTotalPages(providers.getTotalPages());
        providerResponse.setLast(providers.isLast());

        return providerResponse;
    }

    @Override
    public ProviderDTO getProviderById(long id) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", id));
        return mapToDTO(provider);
    }

    @Override
    public ProviderDTO updateProvider(ProviderDTO providerDTO, long id) {
        // Get provider by id from the database
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", id));

        provider.setCedula(providerDTO.getCedula());
        provider.setTradeName(providerDTO.getTradeName());
        provider.setStatus(providerDTO.isStatus());

        Provider updateProvider = providerRepository.save(provider);

        return mapToDTO(updateProvider);
    }

    @Override
    public void deleteProviderById(long id) {
        // Get provider by id from the database
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", id));
        providerRepository.delete(provider);
    }

    @Override
    public List<ProviderDTO> getAllSuppliers() {
        List<Provider>providerList = providerRepository.findAll();
        return providerList.stream().map(provider -> mapToDTO(provider)).collect(Collectors.toList());
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
