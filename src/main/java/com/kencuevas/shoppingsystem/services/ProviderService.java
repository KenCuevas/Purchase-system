package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.ProviderDTO;

import java.util.List;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
public interface ProviderService {

    ProviderDTO createProvider(ProviderDTO providerDTO);

    List<ProviderDTO> getAllProvider();

    ProviderDTO getProviderById(long id);

    ProviderDTO updateProvider(ProviderDTO providerDTO, long id);

    void deleteProviderById(long id);
}
