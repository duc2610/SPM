package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuCauThue;

import java.util.List;

public interface YeuCauThueService {
    List<YeuCauThue> viewYeuCauThueByUserId(int userId);
    void saveYeuCauThue(YeuCauThue yeuCauThue);
    YeuCauThue findYeuCauThueByIdSanBongAndAndIdGioHoatDong(int idSanBong, int idGioHoatDong);
    YeuCauThue findYeuCauThueById(int idYeuCauThue);
    void deleteYeuCauThue(YeuCauThue yeuCauThue);
    List<YeuCauThue> findAllYeuCauThue();
}
