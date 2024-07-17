package com.swp391.SPM.service;

import com.swp391.SPM.entity.LoaiSan;

import java.util.List;

public interface LoaiSanService {
    public LoaiSan getLoaiSanByTen(String tenLoaiSan);
    public List<LoaiSan> getAllLoaiSan();
    LoaiSan findLoaiSansById(int idLoaiSan);
}
