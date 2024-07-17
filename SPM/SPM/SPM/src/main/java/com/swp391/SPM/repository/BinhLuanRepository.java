package com.swp391.SPM.repository;

import com.swp391.SPM.entity.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {
   
    public List<BinhLuan> findBinhLuanByIdSan(int idSan);
    public BinhLuan findBinhLuanById(int idBinhLuan);
}
