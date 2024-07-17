package com.swp391.SPM.entity;

import jakarta.persistence.*;

@Entity(name = "yeuthich")
public class YeuThich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_san_bong")
    private int idSanBong;

    @Column(name = "user_id")
    private int userId;

    public YeuThich() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanBong() {
        return idSanBong;
    }

    public void setIdSanBong(int idSanBong) {
        this.idSanBong = idSanBong;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
