package com.example.duantotnghiep.service;

import com.example.duantotnghiep.dto.request.CustomerRequets;
import com.example.duantotnghiep.dto.response.CustomerResponse;
import com.example.duantotnghiep.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {


    Page<CustomerResponse> getAll(int page, int size);

    CustomerResponse getById(Long id);

    CustomerResponse create(Customer customer);

    CustomerResponse update(Long id, Customer customer);

    void delete(Long id);

    List<CustomerResponse> search(String keyword, Integer status);

    List<Customer> findAll();
}
