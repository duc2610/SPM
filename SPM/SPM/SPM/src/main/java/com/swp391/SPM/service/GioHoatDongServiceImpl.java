package com.swp391.SPM.service;

import com.swp391.SPM.entity.GioHoatDong;
import com.swp391.SPM.repository.GioHoatDongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class GioHoatDongServiceImpl implements GioHoatDongService {
    @Autowired
    private GioHoatDongRepository gioHoatDongRepository;
    @Override
    public GioHoatDong getGioHoatDongById(int idGioHoatDong) {
        return gioHoatDongRepository.getGioHoatDongById(idGioHoatDong);
    }

    @Override
    public void saveGioHoatDong(GioHoatDong gioHoatDong) {
        gioHoatDongRepository.save(gioHoatDong);
    }

    @Override
    public List<GioHoatDong> getAllGioHoatDong() {
        return gioHoatDongRepository.findAll();
    }

    @Override
    public GioHoatDong findGioHoatDongByNgayDaAndGioDa(String ngayDa, Time gioDa) {
        return gioHoatDongRepository.findGioHoatDongByNgayDaAndGioDa(ngayDa, gioDa);
    }

    @Override
    public void deleteGioHoatDong(GioHoatDong gioHoatDong) {
        gioHoatDongRepository.delete(gioHoatDong);
    }

}
