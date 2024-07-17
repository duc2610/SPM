package com.swp391.SPM.dto;

import java.sql.Time;
import java.util.Date;


public class CancellationRequestDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String tenSanBong;
    private String diaDiem;
    private Date ngayTao;
    private String ngayDa;
    private Time gioDa;
    private String ghiChu;

    public CancellationRequestDTO( String fullName, String email, String phoneNumber, String tenSanBong,
                                  String diaDiem, Date ngayTao, String ngayDa, Time gioDa, String ghiChu) {

        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tenSanBong = tenSanBong;
        this.diaDiem = diaDiem;
        this.ngayTao = ngayTao;
        this.ngayDa = ngayDa;
        this.gioDa = gioDa;
        this.ghiChu = ghiChu;}


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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
