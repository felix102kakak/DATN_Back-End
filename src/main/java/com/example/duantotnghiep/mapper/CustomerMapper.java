package com.example.duantotnghiep.mapper;

import com.example.duantotnghiep.dto.request.CustomerRequets;
import com.example.duantotnghiep.dto.response.CustomerResponse;
import com.example.duantotnghiep.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse toCustomerResponse(Customer customer);

    // Ánh xạ danh sách Customer sang danh sách CustomerResponse
    List<CustomerResponse> toResponseList(List<Customer> customers);

    // Ánh xạ từ CustomerRequest sang Customer (cho create hoặc update)
    Customer toCustomer(CustomerRequets customerRequest);
}