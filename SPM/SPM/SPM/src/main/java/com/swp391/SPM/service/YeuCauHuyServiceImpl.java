package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuCauHuy;
import com.swp391.SPM.repository.YeuCauHuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YeuCauHuyServiceImpl implements YeuCauHuyService{
    @Autowired
    YeuCauHuyRepository yeuCauHuyRepository;
    @Override
    public void saveYeuCauHuy(YeuCauHuy yeuCauHuy) {
        yeuCauHuyRepository.save(yeuCauHuy);
    }

    @Override
    public List<YeuCauHuy> getAllYeuCauHuy() {
        return yeuCauHuyRepository.findAll();
    }
}
