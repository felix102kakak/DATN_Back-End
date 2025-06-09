package com.example.duantotnghiep.controller;

import com.example.duantotnghiep.dto.request.CustomerRequets;
import com.example.duantotnghiep.dto.response.CustomerResponse;
import com.example.duantotnghiep.mapper.CustomerMapper;
import com.example.duantotnghiep.model.Customer;
import com.example.duantotnghiep.repository.CustomerRepository;
import com.example.duantotnghiep.service.CustomerService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/customer")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    // Hiển thị phân trang
    @GetMapping("/hien-thi")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CustomerResponse> customerPage = customerService.getAll(page, size);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Lấy danh sách thành công");
        res.put("data", customerPage.getContent());
        res.put("currentPage", customerPage.getNumber());
        res.put("totalItems", customerPage.getTotalElements());
        res.put("totalPages", customerPage.getTotalPages());
        return ResponseEntity.ok(res);
    }

    // Lấy khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        CustomerResponse customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }
    // Tạo mới khách hàng
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequets request) {
        // Dùng Mapper chuyển DTO -> entity
        Customer customer = customerMapper.toCustomer(request);

        // Gọi service tạo customer
        CustomerResponse createdCustomer = customerService.create(customer);

        return ResponseEntity.ok(createdCustomer);
    }
    // Cập nhật khách hàng
    @PutMapping("/{id}/update")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody @Valid CustomerRequets request) {
        // Chuyển DTO thành entity
        Customer customer = customerMapper.toCustomer(request);
        CustomerResponse updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }
    // Xóa mềm khách hàng dùng POST
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        customerService.delete(id);  // gọi service xóa mềm
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status
    ) {
        List<CustomerResponse> customers = customerService.search(keyword, status);
        return ResponseEntity.ok(customers);
    }
    @GetMapping(value = "/export/customers/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> exportCustomersToExcel() {
        List<Customer> customers = customerService.findAll(); // gọi service

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Customers");

            // Tạo tiêu đề cột
            String[] headers = {
                    "ID", "Customer Code", "Customer Name", "Email", "Phone", "Gender",
                    "Date of Birth", "Country", "Province", "District", "Ward", "Address", "Status"
            };
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Format ngày sinh
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Thêm dữ liệu từng dòng
            int rowNum = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getCustomerCode());
                row.createCell(2).setCellValue(customer.getCustomerName());
                row.createCell(3).setCellValue(customer.getEmail());
                row.createCell(4).setCellValue(customer.getPhone());

                // Giới tính
                row.createCell(5).setCellValue(
                        Boolean.TRUE.equals(customer.getGender()) ? "Nam" :
                                Boolean.FALSE.equals(customer.getGender()) ? "Nữ" : ""
                );

                // Ngày sinh
                row.createCell(6).setCellValue(
                        customer.getDateOfBirth() != null ? customer.getDateOfBirth().format(formatter) : ""
                );

                // Địa chỉ
                row.createCell(7).setCellValue(customer.getCountry());
                row.createCell(8).setCellValue(customer.getProvince());
                row.createCell(9).setCellValue(customer.getDistrict());
                row.createCell(10).setCellValue(customer.getWard());
                row.createCell(11).setCellValue(customer.getHouseName());

                // Trạng thái
                row.createCell(12).setCellValue(
                        customer.getStatus() != null && customer.getStatus() == 1 ? "Hoạt động" :
                                customer.getStatus() != null && customer.getStatus() == 0 ? "Ngưng hoạt động" : ""
                );
            }

            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            HttpHeaders headersExcel = new HttpHeaders();
            headersExcel.setContentDisposition(ContentDisposition.attachment().filename("customers.xlsx").build());
            headersExcel.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

            return new ResponseEntity<>(excelBytes, headersExcel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
