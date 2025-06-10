package com.example.duantotnghiep.mapper;

import com.example.duantotnghiep.dto.request.EmployeeRequest;
import com.example.duantotnghiep.dto.response.EmployeeResponse;
import com.example.duantotnghiep.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class  EmployeeMapper {
    public Employee toEntity(EmployeeRequest dto) {
        Employee e = new Employee();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(Employee e, EmployeeRequest dto) {
        e.setEmployeeName(dto.getEmployeeName());
        e.setDateOfBirth(dto.getDateOfBirth());
        e.setGender(dto.getGender());
        e.setPhone(dto.getPhone());
        e.setEmail(dto.getEmail());
        e.setSalary(dto.getSalary());
        e.setPassWord(dto.getPassWord());
        e.setCountry(dto.getCountry());
        e.setProvince(dto.getProvince());
        e.setDistrict(dto.getDistrict());
        e.setWard(dto.getWard());
        e.setHouseName(dto.getHouseName());
        e.setStatus(dto.getStatus());
    }

    public EmployeeResponse toResponse(Employee e) {
        return new EmployeeResponse(
                e.getId(),
                e.getEmployeeCode(),
                e.getEmployeeName(),
                e.getDateOfBirth(),  // LocalDate
                e.getGender(),
                e.getPhone(),
                e.getEmail(),
                e.getSalary(),
                e.getHireDate(),     // LocalDate
                e.getCountry(),
                e.getProvince(),
                e.getDistrict(),
                e.getWard(),
                e.getHouseName(),
                e.getStatus(),
                e.getCreatedDate(),  // LocalDate
                e.getUpdatedDate(),  // LocalDate
                e.getCreatedBy(),
                e.getUpdatedBy()
        );
    }



    public List<EmployeeResponse> toResponseList(List<Employee> list) {
        return list.stream().map(this::toResponse).toList();
    }
}
