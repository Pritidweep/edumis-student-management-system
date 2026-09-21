package com.edumis.edumis.repository;

import com.edumis.edumis.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    Optional<Course> findByCode(String code);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByCodeAndIdNot(String code, Long id);
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByCodeContainingIgnoreCase(String code);
    List<Course> findByDepartmentContainingIgnoreCase(String department);
    List<Course> findByDescriptionContainingIgnoreCase(String description);

    Page<Course> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String name,
        String code,
        String department,
        String description,
        Pageable pageable
    );
    
}