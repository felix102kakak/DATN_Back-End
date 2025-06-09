package com.example.duantotnghiep.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String customerCode;
    private String customerName;
    private String email;
    private String passWord;
    private String phone;
    private Boolean gender;

    private LocalDateTime dateOfBirth;
    private String country;
    private String province;
    private String district;
    private String ward;
    private String houseName;
    private Integer status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;




}
