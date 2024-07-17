package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuCauThue;
import com.swp391.SPM.repository.YeuCauThueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YeuCauThueServiceImpl implements YeuCauThueService{
    @Autowired
    YeuCauThueRepository yeuCauThueRepository;

    @Override
    public List<YeuCauThue> viewYeuCauThueByUserId(int userId) {
        return yeuCauThueRepository.findYeuCauThueByUserId(userId);
    }

    @Override
    public void saveYeuCauThue(YeuCauThue yeuCauThue) {
        yeuCauThueRepository.save(yeuCauThue);
    }

    @Override
    public YeuCauThue findYeuCauThueByIdSanBongAndAndIdGioHoatDong(int idSanBong, int idGioHoatDong) {
        return yeuCauThueRepository.findYeuCauThueByIdSanBongAndAndIdGioHoatDong(idSanBong, idGioHoatDong);
    }

    @Override
    public YeuCauThue findYeuCauThueById(int idYeuCauThue) {
        return yeuCauThueRepository.findYeuCauThueById(idYeuCauThue);
    }

    @Override
    public void deleteYeuCauThue(YeuCauThue yeuCauThue) {
        yeuCauThueRepository.delete(yeuCauThue);
    }

    @Override
    public List<YeuCauThue> findAllYeuCauThue() {
        return yeuCauThueRepository.findAll();
    }

}
