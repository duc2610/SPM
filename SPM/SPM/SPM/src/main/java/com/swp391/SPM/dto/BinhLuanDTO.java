package com.swp391.SPM.dto;

import com.swp391.SPM.entity.User;

import java.util.Date;

public class BinhLuanDTO {
    private String noiDung;
    private int idBinhLuan;
    private Date createDate;
    private User user;

    public BinhLuanDTO() {
    }


    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(int idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
