package com.edumis.edumis.controller;

import com.edumis.edumis.dto.DepartmentDto;
import com.edumis.edumis.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(
            @Valid @RequestBody DepartmentDto departmentDto) {

        DepartmentDto createdDepartment =
                departmentService.createDepartment(departmentDto);

        return new ResponseEntity<>(
                createdDepartment,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {

    return departmentService.getAllDepartments(pageable);
    
    }

   // READ BY ID
   @GetMapping("/{id}")
   public ResponseEntity<DepartmentDto> getDepartmentById(
        @PathVariable Long id) {

    return ResponseEntity.ok(
            departmentService.getDepartmentById(id)
    );
   
   }

   // UPDATE
   @PutMapping("/{id}")
   public ResponseEntity<DepartmentDto> updateDepartment(
        @PathVariable Long id,
        @Valid @RequestBody DepartmentDto departmentDto) {

    return ResponseEntity.ok(
            departmentService.updateDepartment(id, departmentDto)
    );

   }

   // DELETE
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteDepartment(
        @PathVariable Long id) {

    departmentService.deleteDepartment(id);

    return ResponseEntity.noContent().build();
  
    }
    
    // SEARCH BY NAME
    @GetMapping("/search/name")
    public ResponseEntity<List<DepartmentDto>> searchByName(
        @RequestParam String name) {

     return ResponseEntity.ok(
            departmentService.searchByName(name)
     );

    }
    
    // SEARCH BY CODE
    @GetMapping("/search/code")
    public ResponseEntity<List<DepartmentDto>> searchByCode(
        @RequestParam String code) {

    return ResponseEntity.ok(
            departmentService.searchByCode(code)
    );
   
    }

    // SEARCH BY DESCRIPTION
    @GetMapping("/search/description")
    public ResponseEntity<List<DepartmentDto>> searchByDescription(
        @RequestParam String description) {

    return ResponseEntity.ok(
            departmentService.searchByDescription(description)
    );
 
    }

    // UNIVERSAL SEARCH
    @GetMapping("/search")
    public Page<DepartmentDto> searchDepartments(
        @RequestParam String query,
        Pageable pageable) {
            
            return departmentService.searchDepartments(query, pageable);
        
        }

}
