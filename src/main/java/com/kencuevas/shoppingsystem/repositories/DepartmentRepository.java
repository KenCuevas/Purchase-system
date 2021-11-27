package com.kencuevas.shoppingsystem.repositories;

import com.kencuevas.shoppingsystem.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
