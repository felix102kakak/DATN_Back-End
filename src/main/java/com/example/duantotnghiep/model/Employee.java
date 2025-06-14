package com.example.duantotnghiep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "employee_code", nullable = false, length = 50)
    private String employeeCode;

    @Size(max = 250)
    @NotNull
    @Nationalized
    @Column(name = "employee_name", nullable = false, length = 250)
    private String employeeName;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;


    @NotNull
    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Size(max = 50)
    @NotNull
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Size(max = 250)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @NotNull
    @Column(name = "salary", nullable = false, precision = 18, scale = 3)
    private BigDecimal salary;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "pass_word", nullable = false, length = 200)
    private String passWord;

    @Size(max = 50)
    @Nationalized
    @Column(name = "country", length = 50)
    private String country;

    @Size(max = 100)
    @Nationalized
    @Column(name = "province", length = 100)
    private String province;

    @Size(max = 100)
    @Nationalized
    @Column(name = "district", length = 100)
    private String district;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ward", length = 100)
    private String ward;

    @Size(max = 250)
    @Nationalized
    @Column(name = "house_name", length = 250)
    private String houseName;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "updated_by", length = 50)
    private String updatedBy;
//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false)
//    private Role role;
    @Column(name = "role_id", nullable = false)
    private Long roleId = 1L;

}