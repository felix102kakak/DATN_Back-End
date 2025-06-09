package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE " +
            "(:keyword IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:status IS NULL OR c.status = :status)")
    List<Customer> search(
            @Param("keyword") String keyword,
            @Param("status") Integer status);
    List<Customer> findByStatus(Integer status);
    Page<Customer> findByStatus(Integer status, Pageable pageable);
}