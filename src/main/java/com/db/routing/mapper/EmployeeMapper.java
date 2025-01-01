package com.db.routing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.db.routing.dto.EmployeeDto;
import com.db.routing.entity.Employee;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    Employee toEntity(EmployeeDto.EmployeeRequest dto);
    EmployeeDto.EmployeeResponse toDto(Employee entity);
}
