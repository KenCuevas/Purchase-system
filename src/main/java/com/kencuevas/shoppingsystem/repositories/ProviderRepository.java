package com.kencuevas.shoppingsystem.repositories;

import com.kencuevas.shoppingsystem.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
