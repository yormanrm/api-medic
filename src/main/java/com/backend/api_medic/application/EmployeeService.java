package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Employee;
import com.backend.api_medic.domain.ports.IEmployeeRepository;
import com.backend.api_medic.infrastructure.dto.response.NewEmployeeDTO;

public class EmployeeService {
    private final IEmployeeRepository iEmployeeRepository;

    public EmployeeService(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public NewEmployeeDTO save(Employee employee, String role) {
        return iEmployeeRepository.save(employee, role);
    }

    public Iterable<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return iEmployeeRepository.findById(id);
    }

    public Iterable<Employee> searchBySomeTextfield(String textfield) {
        return iEmployeeRepository.searchBySomeTextfield(textfield);
    }

    public void deleteById(Integer id, String role) {
        iEmployeeRepository.deleteById(id, role);
    }
}