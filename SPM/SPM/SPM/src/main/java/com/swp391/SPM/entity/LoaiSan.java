package com.swp391.SPM.entity;

import jakarta.persistence.*;

@Entity(name = "loaisan")
public class LoaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_loai_san")
    private String tenLoaiSan;


    public LoaiSan() {
    }


    public String getTenLoaiSan() {
        return tenLoaiSan;
    }

    public void setTenLoaiSan(String tenLoaiSan) {
        this.tenLoaiSan = tenLoaiSan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
