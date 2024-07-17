package com.swp391.SPM.service;

import com.swp391.SPM.entity.YeuThich;

import java.util.List;

public interface YeuThichService {
    public List<YeuThich> getListYeuThich(int userId);
    public void deleteSanBongYeuThich(int userId, int idSanBong);
    public YeuThich getYeuThich(int userId, int idSanBong);
    public void addYeuThichList(YeuThich yeuThich);
}
