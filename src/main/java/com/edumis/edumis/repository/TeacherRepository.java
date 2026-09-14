package com.edumis.edumis.repository;

import com.edumis.edumis.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Teacher> findByFirstNameContainingIgnoreCase(String firstName);
    List<Teacher> findByLastNameContainingIgnoreCase(String lastName);
    List<Teacher> findByDepartmentContainingIgnoreCase(String department);

    Page<Teacher> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrPhoneContainingIgnoreCase(
        String firstName,
        String lastName,
        String email,
        String department,
        String phone,
        Pageable pageable
    
    );

}