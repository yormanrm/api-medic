package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.EmployeeService;
import com.backend.api_medic.infrastructure.dto.request.EmployeeDTO;
import com.backend.api_medic.infrastructure.dto.request.SearchTextRequestDTO;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
//@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@Valid @RequestBody EmployeeDTO employeeDTO) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                201,
                false,
                "Created employee",
                employeeService.save(employeeDTO.getEmployee(), employeeDTO.getRole())
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDTO<Object>> getAll() {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Employees found",
                employeeService.findAll()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-byID")
    public ResponseEntity<ApiResponseDTO<Object>> getByID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Employee found with ID equals to " + id,
                employeeService.findById(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponseDTO<Object>> search(@RequestBody SearchTextRequestDTO searchTextRequestDTO) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Employees found with full name or professional license similar to " + searchTextRequestDTO.getSearchText(),
                employeeService.searchBySomeTextfield(searchTextRequestDTO.getSearchText())
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}