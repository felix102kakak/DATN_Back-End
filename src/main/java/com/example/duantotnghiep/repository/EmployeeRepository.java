package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE " +
            "(:keyword IS NULL OR LOWER(e.employeeName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(e.phone) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:status IS NULL OR e.status = :status)")
    List<Employee> search(
            @Param("keyword") String keyword,
            @Param("status") Integer status);

    List<Employee> findByStatus(Integer status);

    Page<Employee> findByStatus(Integer status, Pageable pageable);
}
