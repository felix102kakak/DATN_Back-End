package com.example.duantotnghiep.service.impl;

import com.example.duantotnghiep.dto.request.EmployeeRequest;
import com.example.duantotnghiep.dto.response.EmployeeResponse;
import com.example.duantotnghiep.model.Employee;
import com.example.duantotnghiep.mapper.EmployeeMapper;
import com.example.duantotnghiep.repository.EmployeeRepository;
import com.example.duantotnghiep.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Page<EmployeeResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findByStatus(1, pageable);
        return employeePage.map(employeeMapper::toResponse);
    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {

        Employee employee = employeeMapper.toEntity(request);
        String newCode = generateEmployeeCode();
        employee.setEmployeeCode(newCode);
        employee.setStatus(1);
        employee.setHireDate(LocalDate.now());

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toResponse(saved);
    }

    private String generateEmployeeCode() {
        long count = employeeRepository.count();
        return String.format("EMP%04d", count + 1);
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employeeMapper.updateEntity(existing, request);
        existing.setHireDate(LocalDate.now()); // nếu bạn vẫn muốn cập nhật ngày thuê
        // Cập nhật lại ngày thuê nếu muốn

        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employee.setStatus(0); // đánh dấu đã xóa mềm
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeResponse> search(String keyword, Integer status) {
        List<Employee> employees = employeeRepository.search(keyword, status);
        return employeeMapper.toResponseList(employees);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
