package com.swp391.SPM.service;

import com.swp391.SPM.entity.GioHoatDong;

import java.sql.Time;
import java.util.List;

public interface GioHoatDongService {
    GioHoatDong getGioHoatDongById(int idGioHoatDong);
    void saveGioHoatDong(GioHoatDong gioHoatDong);
    List<GioHoatDong> getAllGioHoatDong();
    GioHoatDong findGioHoatDongByNgayDaAndGioDa(String ngayDa, Time gioDa);
    void deleteGioHoatDong(GioHoatDong gioHoatDong);
}
