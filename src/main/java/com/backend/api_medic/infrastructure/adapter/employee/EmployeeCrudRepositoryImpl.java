package com.backend.api_medic.infrastructure.adapter.employee;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.domain.model.Employee;
import com.backend.api_medic.domain.ports.IEmployeeRepository;
import com.backend.api_medic.infrastructure.adapter.credential.ICredentialCrudRepository;
import com.backend.api_medic.infrastructure.dto.response.NewEmployeeDTO;
import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import com.backend.api_medic.infrastructure.entity.EmployeeEntity;
import com.backend.api_medic.infrastructure.exception.EmptyIterableException;
import com.backend.api_medic.infrastructure.exception.ResourceAlreadyExistsException;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.CredentialMapper;
import com.backend.api_medic.infrastructure.mapper.EmployeeMapper;
import com.backend.api_medic.infrastructure.utils.GenerateCredentials;
import com.backend.api_medic.infrastructure.utils.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeCrudRepositoryImpl implements IEmployeeRepository {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IEmployeeCrudRepository iEmployeeCrudRepository;

    @Autowired
    private ICredentialCrudRepository iCredentialCrudRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private GenerateCredentials generateCredentials;

    @Override
    public NewEmployeeDTO save(Employee employee, String role) {
        Optional<EmployeeEntity> optionalEmployeeEntity = iEmployeeCrudRepository.findByFullName(employee.getFullName());
        if (optionalEmployeeEntity.isPresent()) {
            throw new ResourceAlreadyExistsException("A employee has been registered with name " + employee.getFullName());
        } else {
            Employee savedEmployee = employeeMapper.toEmployee(iEmployeeCrudRepository.save(employeeMapper.toEmployeeEntity(employee)));
            try {
                String generatedUsername = generateCredentials.generateUsername(employee.getFullName());
                String generatedPassword = generateCredentials.generatePassword(employee.getFullName());
                Credential credential = new Credential(
                        null,
                        savedEmployee.getId(),
                        generatedUsername,
                        bCryptPasswordEncoder.encode(generatedPassword),
                        role,
                        null,
                        null,
                        false);
                iCredentialCrudRepository.save(credentialMapper.toCredentialEntity(credential));
                NewEmployeeDTO newEmployeeDTO = new NewEmployeeDTO(
                        savedEmployee.getFullName(),
                        generatedUsername,
                        generatedPassword,
                        credential.getRole()
                );
                return newEmployeeDTO;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Iterable<Employee> findAll() {
        Iterable<Employee> employees = employeeMapper.toEmployees(iEmployeeCrudRepository.findAll());
        if(IterableUtils.isEmpty(employees)) {
            throw new EmptyIterableException("There are no registered employees");
        } else {
            return employees;
        }
    }

    @Override
    public Employee findById(Integer id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = iEmployeeCrudRepository.findById(id);
        if(optionalEmployeeEntity.isPresent()) {
            return employeeMapper.toEmployee(optionalEmployeeEntity.get());
        } else {
            throw new ResourceNotFoundException("Employee with ID " + id + " not found");
        }
    }

    @Override
    public Iterable<Employee> searchBySomeTextfield(String textfield) {
        Iterable<Employee> employees = employeeMapper.toEmployees(iEmployeeCrudRepository.searchBySomeTextfield(textfield));
        if(IterableUtils.isEmpty(employees)) {
            throw new EmptyIterableException("There are no registered employees with a full name or professional license similar to " + textfield);
        } else {
            return employees;
        }
    }

    @Override
    public void deleteById(Integer id, String role) {
        Optional<EmployeeEntity> optionalEmployeeEntity = iEmployeeCrudRepository.findById(id);
        if (optionalEmployeeEntity.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            employeeEntity.setLogicallyRemoved(true);
            iEmployeeCrudRepository.save(employeeEntity);
            Optional<CredentialEntity> optionalCredentialEntity = iCredentialCrudRepository.findByEmployeeIdAndRole(id, role);
            if (optionalCredentialEntity.isPresent()) {
                CredentialEntity credentialEntity = optionalCredentialEntity.get();
                credentialEntity.setLogicallyRemoved(true);
                iCredentialCrudRepository.save(credentialEntity);
            } else {
                throw new ResourceNotFoundException("Credential for employee with ID " + id + " not found");
            }
        } else {
            throw new ResourceNotFoundException("Employee with ID " + id + " not found");
        }
    }

    @Override
    public void update(Employee employee) {
        Optional<EmployeeEntity> optionalEmployeeEntity = iEmployeeCrudRepository.findById(employee.getId());
        if (optionalEmployeeEntity.isPresent()) {
            try {
                EmployeeEntity existingEntity = optionalEmployeeEntity.get();
                boolean hasChanges = !existingEntity.getFullName().equals(employee.getFullName()) ||
                                !existingEntity.getBirthday().equals(employee.getBirthday()) ||
                                !existingEntity.getAcademicDegree().equals(employee.getAcademicDegree()) ||
                                !existingEntity.getProfessionalLicense().equals(employee.getProfessionalLicense()) ||
                                !existingEntity.getTelephone().equals(employee.getTelephone()) ||
                                !existingEntity.getEmail().equals(employee.getEmail());

                if (hasChanges) {
                    existingEntity.setFullName(employee.getFullName());
                    existingEntity.setBirthday(employee.getBirthday());
                    existingEntity.setAcademicDegree(employee.getAcademicDegree());
                    existingEntity.setProfessionalLicense(employee.getProfessionalLicense());
                    existingEntity.setTelephone(employee.getTelephone());
                    existingEntity.setEmail(employee.getEmail());

                    iEmployeeCrudRepository.save(existingEntity);
                } else {
                    throw new RuntimeException("No changes detected for employee with ID " + employee.getId());
                }

            } catch (Exception e) {
                throw new RuntimeException("Error updating employee with ID " + employee.getId() + ": " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("Employee with ID " + employee.getId() + " not found");
        }
    }
}