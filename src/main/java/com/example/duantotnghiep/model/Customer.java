package com.example.duantotnghiep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.management.relation.Role;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "customer_code", length = 50)
    private String customerCode;

    @Size(max = 255)
    @Nationalized
    @Column(name = "customer_name")
    private String customerName;

    @Size(max = 255)
    @Nationalized
    @Column(name = "email")
    private String email;

    @Size(max = 250)
    @Nationalized
    @Column(name = "pass_word", length = 250)
    private String passWord;

    @Size(max = 50)
    @Nationalized
    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "date_of_birth")
    private LocalDateTime  dateOfBirth;

    @Size(max = 100)
    @Nationalized
    @Column(name = "country", length = 100)
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
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime  updatedDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role ;



}