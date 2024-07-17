package com.swp391.SPM.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "sanbong")
public class SanBong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column( name = "id")
    private int id;

    @Column( name = "ten_san_bong")
    private String tenSanBong;

    @Column(name = "dia_diem")
    private String diaDiem;

    @Column(name = "tien_san")
    private int tienSan;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "mo_ta_san")
    private String moTaSan;

    @Column(name = "id_loai_san")
    private int idLoaiSan;

}
