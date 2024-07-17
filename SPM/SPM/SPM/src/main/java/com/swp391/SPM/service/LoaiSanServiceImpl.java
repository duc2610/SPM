package com.swp391.SPM.service;

import com.swp391.SPM.entity.LoaiSan;
import com.swp391.SPM.repository.LoaiSanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiSanServiceImpl implements LoaiSanService{
    @Autowired
    LoaiSanRepository loaiSanRepository;
    @Override
    public LoaiSan getLoaiSanByTen(String tenLoaiSan) {
        return loaiSanRepository.findLoaiSansByTenLoaiSan(tenLoaiSan);
    }

    @Override
    public List<LoaiSan> getAllLoaiSan() {
        return loaiSanRepository.findAll();
    }

    @Override
    public LoaiSan findLoaiSansById(int idLoaiSan) {
        return loaiSanRepository.findLoaiSansById(idLoaiSan);
    }
}
