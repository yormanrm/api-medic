package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Employee;
import com.backend.api_medic.infrastructure.dto.response.NewEmployeeDTO;

public interface IEmployeeRepository {
    NewEmployeeDTO save(Employee employee, String role);

    Iterable<Employee> findAll();

    Employee findById(Integer id);

    Iterable<Employee> searchBySomeTextfield(String textfield);

    void deleteById(Integer id, String role);

    void update(Employee employee);
}