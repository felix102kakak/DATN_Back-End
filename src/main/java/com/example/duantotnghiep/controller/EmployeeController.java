package com.example.duantotnghiep.controller;

import com.example.duantotnghiep.dto.request.EmployeeRequest;
import com.example.duantotnghiep.dto.response.EmployeeResponse;
import com.example.duantotnghiep.model.Employee;
import com.example.duantotnghiep.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    // ✅ Lấy danh sách có phân trang
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(employeeService.getAll(page, size));
    }

    // ✅ Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    // ✅ Tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> search(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer status
    ) {
        return ResponseEntity.ok(employeeService.search(keyword, status));
    }

    // ✅ Thêm mới
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.create(request));
    }

    // ✅ Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request
    ) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    // ✅ Xoá mềm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy tất cả (không phân trang)
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllWithoutPaging() {
        return ResponseEntity.ok(employeeService.findAll());
    }
}
