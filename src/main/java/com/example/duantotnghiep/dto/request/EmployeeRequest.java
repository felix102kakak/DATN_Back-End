package com.example.duantotnghiep.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String employeeCode;
    private String employeeName;
    private LocalDate  dateOfBirth;
    private Integer gender;
    private String phone;
    private String email;
    private BigDecimal salary;
    private LocalDate  hireDate;
    private String passWord;
    private String country;
    private String province;
    private String district;
    private String ward;
    private String houseName;
    private Integer status;
}
