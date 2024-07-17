package com.swp391.SPM.handle;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public  class RegisterUser {
    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min = 1, message = "Độ dài tối thiểu là 1")
    private String fullname;
    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min=8, message = "Độ dài tối thiểu là 8")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Mật khẩu phải có ít nhất một chữ số và một ký tự đặc biệt")
    private String password;
    @NotBlank(message = "Thông tin bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Thông tin bắt buộc")
    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải bắt đầu bằng số 0 và có đúng 10 chữ số")
    private String phone;


    public RegisterUser() {
    }

    public RegisterUser(String fullname, String password, String email, String phone) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
