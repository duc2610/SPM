package com.swp391.SPM.service;

import com.swp391.SPM.entity.BinhLuan;

import java.util.List;

public interface BinhLuanService {
    List<BinhLuan> getAllBinhLuan(int idSan);
    BinhLuan getBinhLuanById(int idBinhLuan);
    void saveBinhLuan(BinhLuan binhLuan);
    void deleteBinhLuan(BinhLuan binhLuan);
}
