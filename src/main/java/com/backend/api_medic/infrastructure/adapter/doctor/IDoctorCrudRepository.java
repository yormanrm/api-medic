package com.backend.api_medic.infrastructure.adapter.doctor;

import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDoctorCrudRepository extends CrudRepository<DoctorEntity, Integer> {

    Optional<DoctorEntity> findByFullName(String fullName);

    @Query("SELECT d FROM DoctorEntity d WHERE " +
            "LOWER(d.fullName) LIKE LOWER(CONCAT('%', :textfield, '%')) " +
            "OR LOWER(d.specialty) LIKE LOWER(CONCAT('%', :textfield, '%')) " +
            "OR LOWER(d.professionalLicense) LIKE LOWER(CONCAT('%', :textfield, '%'))"
    )
    Iterable<DoctorEntity> searchBySomeTextfield(@Param("textfield") String textfield);

}