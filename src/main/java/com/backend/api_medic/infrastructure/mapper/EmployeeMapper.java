package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Employee;
import com.backend.api_medic.infrastructure.entity.EmployeeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "birthday", target = "birthday"),
            @Mapping(source = "academicDegree", target = "academicDegree"),
            @Mapping(source = "professionalLicense", target = "professionalLicense"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated"),
            @Mapping(source = "logicallyRemoved", target = "logicallyRemoved")
    })
    Employee toEmployee(EmployeeEntity employeeEntity);

    Iterable<Employee> toEmployees(Iterable<EmployeeEntity> employeeEntities);

    @InheritInverseConfiguration
    EmployeeEntity toEmployeeEntity(Employee employee);
}