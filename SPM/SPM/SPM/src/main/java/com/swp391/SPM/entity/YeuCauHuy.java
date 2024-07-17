package com.swp391.SPM.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "yeucauhuy")
public class YeuCauHuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ngay_da")
    private String ngayDa;

    @Column(name = "gio_da")
    private Time gioDa;

    @Column(name = "id_san_bong")
    private int idSanBong;

    public YeuCauHuy(String ghiChu, Date ngayTao, int userId, String ngayDa, Time gioDa, int idSanBong) {
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.userId = userId;
        this.ngayDa = ngayDa;
        this.gioDa = gioDa;
        this.idSanBong = idSanBong;
    }
}
