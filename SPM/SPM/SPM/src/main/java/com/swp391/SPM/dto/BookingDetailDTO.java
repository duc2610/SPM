package com.swp391.SPM.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;


public class BookingDetailDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String tenSanBong;
    private String diaDiem;
    private int tienSan;
    private String ngayDa;
    private Time gioDa;
    private Date ngayTao;

    public BookingDetailDTO( String fullName, String email, String phoneNumber, String tenSanBong,
                            String diaDiem, int tienSan,
                            String ngayDa, Time gioDa, Date ngayTao) {

        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tenSanBong = tenSanBong;
        this.diaDiem = diaDiem;
        this.tienSan = tienSan;
        this.ngayDa = ngayDa;
        this.gioDa = gioDa;
        this.ngayTao = ngayTao;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTenSanBong() {
        return tenSanBong;
    }

    public void setTenSanBong(String tenSanBong) {
        this.tenSanBong = tenSanBong;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public double getTienSan() {
        return tienSan;
    }

    public void setTienSan(int tienSan) {
        this.tienSan = tienSan;
    }

    public String getNgayDa() {
        return ngayDa;
    }

    public void setNgayDa(String ngayDa) {
        this.ngayDa = ngayDa;
    }

    public Time getGioDa() {
        return gioDa;
    }

    public void setGioDa(Time gioDa) {
        this.gioDa = gioDa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}



