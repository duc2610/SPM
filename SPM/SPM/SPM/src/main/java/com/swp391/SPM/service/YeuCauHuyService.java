package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuCauHuy;

import java.util.List;

public interface YeuCauHuyService {
    void saveYeuCauHuy(YeuCauHuy yeuCauHuy);
    List<YeuCauHuy> getAllYeuCauHuy();
}
