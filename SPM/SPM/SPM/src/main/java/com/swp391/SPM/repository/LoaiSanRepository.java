package com.swp391.SPM.repository;

import com.swp391.SPM.entity.LoaiSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanRepository extends JpaRepository<LoaiSan, Integer> {
    public LoaiSan findLoaiSansByTenLoaiSan(String tenLoaiSan);
    LoaiSan findLoaiSansById(int idLoaiSan);
}
