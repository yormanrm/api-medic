package com.backend.api_medic.infrastructure.adapter.patient;

import com.backend.api_medic.infrastructure.entity.PatientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPatientCrudRepository extends CrudRepository<PatientEntity, Integer> {

    Optional<PatientEntity> findByFullName(String name);

    @Query("SELECT p FROM PatientEntity p WHERE LOWER(p.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Iterable<PatientEntity> searchByFullName(@Param("fullName") String fullName);

}