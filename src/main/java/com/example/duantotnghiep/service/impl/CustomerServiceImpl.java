package com.example.duantotnghiep.service.impl;

import com.example.duantotnghiep.dto.response.CustomerResponse;
import com.example.duantotnghiep.mapper.CustomerMapper;
import com.example.duantotnghiep.model.Customer;
import com.example.duantotnghiep.repository.CustomerRepository;
import com.example.duantotnghiep.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;


    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findByStatus(1, pageable);
        return customerPage.map(customerMapper::toCustomerResponse);
    }


    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        return customerMapper.toCustomerResponse(customer);
    }
    @Override
    public CustomerResponse create(Customer customer) {
        // Tự sinh mã customerCode trước khi lưu
        String newCode = generateCustomerCode();
        customer.setCustomerCode(newCode);

        // Lưu vào DB
        Customer savedCustomer = customerRepository.save(customer);

        // Convert sang DTO
        return customerMapper.toCustomerResponse(savedCustomer);
    }

    // Hàm sinh mã tự động: CUS0001, CUS0002,...
    private String generateCustomerCode() {
        long count = customerRepository.count();
        return String.format("CUS%04d", count + 1);
    }
    @Override
    public CustomerResponse update(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        // Copy dữ liệu từ customer vào existingCustomer, hoặc cập nhật từng field
        // Có thể dùng BeanUtils hoặc thủ công
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setDateOfBirth(customer.getDateOfBirth());
        existingCustomer.setCountry(customer.getCountry());
        existingCustomer.setProvince(customer.getProvince());
        existingCustomer.setDistrict(customer.getDistrict());
        existingCustomer.setWard(customer.getWard());
        existingCustomer.setHouseName(customer.getHouseName());
        existingCustomer.setStatus(customer.getStatus());
        existingCustomer.setUpdatedDate(LocalDateTime.now());
        // Cập nhật thêm các trường khác nếu cần

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(updatedCustomer);
    }
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        customer.setStatus(0); // đánh dấu đã xóa mềm
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> search(String keyword, Integer status) {
        List<Customer> customers = customerRepository.search(keyword, status);
        return customerMapper.toResponseList(customers);
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


}

