package com.swp391.SPM.service;

import com.swp391.SPM.entity.SanBong;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SanBongService {
    public SanBong getSanBongById(int sanBongId);
    public List<SanBong> getAllSanBong();
    public void deleteSanBongById(int idSanBong);

    List<SanBong> searchByName(String keyword);

    Page<SanBong> getAll(Integer pageNo);

    Page<SanBong> searchByName(String keyword,Integer pageNo);
    public Page<SanBong> getSanBongByIdLoaiSan(int idLoaiSan, Integer pageNo);
    Boolean deleteById(Integer id);
    Boolean update(SanBong sanBong);

    Boolean create(SanBong sanBong);

}
