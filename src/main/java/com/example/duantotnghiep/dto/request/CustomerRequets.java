package com.example.duantotnghiep.dto.request;

import jakarta.validation.constraints.*;
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
public class CustomerRequets {

    private Integer id;

    //@NotBlank(message = "Mã khách hàng không được để trống")
    //@Size(min = 3, max = 20, message = "Mã khách hàng phải từ 3 đến 20 ký tự")
    private String customerCode;

    @NotBlank(message = "Tên khách hàng không được để trống")
    private String customerName;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String passWord;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDateTime dateOfBirth;

    @NotBlank(message = "Quốc gia không được để trống")
    private String country;

    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province;

    @NotBlank(message = "Quận/Huyện không được để trống")
    private String district;

    @NotBlank(message = "Phường/Xã không được để trống")
    private String ward;

    @NotBlank(message = "Tên nhà không được để trống")
    private String houseName;

    @NotNull(message = "Trạng thái không được để trống")
    private Integer status;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String createdBy;
    private String updatedBy;
}
