package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;

import java.util.List;

public interface ProviderService {

    ProviderDTO createProvider(ProviderDTO providerDTO);

    List<ProviderDTO> getAllProvider();
}
