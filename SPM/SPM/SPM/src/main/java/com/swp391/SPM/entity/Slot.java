package com.swp391.SPM.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_san_bong")
    private int idSanBong;

    @Column(name = "id_gio_hoat_dong")
    private int idGioHoatDong;

    @Column(name = "trang_thai")
    private boolean trangThai;

    public Slot(int idSanBong, int idGioHoatDong, boolean trangThai) {
        this.idSanBong = idSanBong;
        this.idGioHoatDong = idGioHoatDong;
        this.trangThai = trangThai;
    }
}
