package com.swp391.SPM.repository;

import com.swp391.SPM.entity.GioHoatDong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;

@Repository
public interface GioHoatDongRepository extends JpaRepository<GioHoatDong, Integer> {
    public GioHoatDong getGioHoatDongById(int idGioHoatDong);
    GioHoatDong findGioHoatDongByNgayDaAndGioDa(String ngayDa, Time gioDa);
}
