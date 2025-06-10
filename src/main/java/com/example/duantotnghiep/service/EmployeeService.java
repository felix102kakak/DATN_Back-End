package com.example.duantotnghiep.service;

import com.example.duantotnghiep.dto.request.EmployeeRequest;
import com.example.duantotnghiep.dto.response.EmployeeResponse;
import com.example.duantotnghiep.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<EmployeeResponse> getAll(int page, int size);

    EmployeeResponse getById(Long id);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);

    List<EmployeeResponse> search(String keyword, Integer status);

    List<Employee> findAll();
}
