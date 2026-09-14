package com.edumis.edumis.repository;

import com.edumis.edumis.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    Optional<Department> findByCode(String code);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByCodeAndIdNot(String code, Long id);

    List<Department> findByNameContainingIgnoreCase(String name);
    List<Department> findByCodeContainingIgnoreCase(String code);
    List<Department> findByDescriptionContainingIgnoreCase(String description);

    Page<Department> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String name,
        String code,
        String description,
        Pageable pageable
    );


}