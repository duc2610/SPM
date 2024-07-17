package com.swp391.SPM.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "yeucauthue")
public class YeuCauThue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "id_san_bong")
    private int idSanBong;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "id_gio_hoat_dong")
    private int idGioHoatDong;

    public YeuCauThue(int userId, int idSanBong, Date ngayTao, int idGioHoatDong) {
        this.userId = userId;
        this.idSanBong = idSanBong;
        this.ngayTao = ngayTao;
        this.idGioHoatDong = idGioHoatDong;
    }
}
