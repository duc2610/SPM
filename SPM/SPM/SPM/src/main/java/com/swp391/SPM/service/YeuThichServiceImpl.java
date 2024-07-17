package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuThich;
import com.swp391.SPM.repository.YeuThichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YeuThichServiceImpl implements YeuThichService{
    @Autowired
    YeuThichRepository yeuThichRepository;
    @Override
    public List<YeuThich> getListYeuThich(int userId) {
        return yeuThichRepository.findYeuThichByUserId(userId);
    }

    @Override
    public void deleteSanBongYeuThich(int userId, int idSanBong) {
        YeuThich yeuThich = yeuThichRepository.findYeuThichByUserIdAndIdSanBong(userId, idSanBong);
        yeuThichRepository.delete(yeuThich);
    }

    @Override
    public YeuThich getYeuThich(int userId, int idSanBong) {
        return yeuThichRepository.findYeuThichByUserIdAndIdSanBong(userId, idSanBong);
    }

    @Override
    public void addYeuThichList(YeuThich yeuThich) {
        yeuThichRepository.save(yeuThich);
    }
}
