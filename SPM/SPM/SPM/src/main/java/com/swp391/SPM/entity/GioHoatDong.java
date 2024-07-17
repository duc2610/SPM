package com.swp391.SPM.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "giohoatdong")
public class GioHoatDong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "gio_da")
    private Time gioDa;

    @Column(name = "ngay_da")
    private String ngayDa;

    public GioHoatDong(Time gioDa, String ngayDa) {
        this.gioDa = gioDa;
        this.ngayDa = ngayDa;
    }

}
