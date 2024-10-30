package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.DoctorService;
import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.infrastructure.dto.request.SearchTextRequestDTO;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
@CrossOrigin(origins = "http://localhost:4200/")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@Valid @RequestBody Doctor doctor) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                201,
                false,
                "Created doctor",
                doctorService.save(doctor)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDTO<Object>> getAll() {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Doctors found",
                doctorService.findAll()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-byID")
    public ResponseEntity<ApiResponseDTO<Object>> getByID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
          200,
          false,
          "Doctor found with ID equals to " + id,
          doctorService.findById(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponseDTO<Object>> search(@RequestBody SearchTextRequestDTO searchTextRequestDTO) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Doctors found with full name, specialty or professional license similar to " + searchTextRequestDTO.getSearchText(),
                doctorService.searchBySomeTextfield(searchTextRequestDTO.getSearchText())
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}