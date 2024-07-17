package com.swp391.SPM.service;

import com.swp391.SPM.entity.BinhLuan;
import com.swp391.SPM.repository.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinhLuanServiceImpl implements BinhLuanService {
    @Autowired
    BinhLuanRepository binhLuanRepository;
    @Override
    public List<BinhLuan> getAllBinhLuan(int idSan) {
        return binhLuanRepository.findBinhLuanByIdSan(idSan);
    }

    @Override
    public BinhLuan getBinhLuanById(int idBinhLuan) {
        return binhLuanRepository.findBinhLuanById(idBinhLuan);
    }

    @Override
    public void saveBinhLuan(BinhLuan binhLuan) {
        binhLuanRepository.save(binhLuan);
    }

    @Override
    public void deleteBinhLuan(BinhLuan binhLuan) {
        binhLuanRepository.delete(binhLuan);
    }
}
