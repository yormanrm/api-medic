package com.backend.api_medic.infrastructure.adapter.employee;

import com.backend.api_medic.infrastructure.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IEmployeeCrudRepository extends CrudRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByFullName(String fullName);

    @Query("SELECT d FROM EmployeeEntity d WHERE " +
            "LOWER(d.fullName) LIKE LOWER(CONCAT('%', :textfield, '%')) " +
            "OR LOWER(d.professionalLicense) LIKE LOWER(CONCAT('%', :textfield, '%'))"
    )
    Iterable<EmployeeEntity> searchBySomeTextfield(@Param("textfield") String textfield);

}