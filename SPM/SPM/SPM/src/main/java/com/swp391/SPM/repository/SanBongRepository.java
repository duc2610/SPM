package com.swp391.SPM.repository;

import com.swp391.SPM.entity.SanBong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanBongRepository extends JpaRepository<SanBong, Integer> {
    @Query("SELECT sb FROM SanBong sb WHERE sb.tenSanBong LIKE %?1%")
    List<SanBong> searchByName(String keyword);

    public SanBong findSanBongsById(int sanBongId);
    public List<SanBong> getSanBongByIdLoaiSan(int idLoaiSan);
}
