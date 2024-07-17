package com.swp391.SPM.repository;

import com.swp391.SPM.entity.YeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface YeuThichRepository extends JpaRepository<YeuThich, Integer> {
    public List<YeuThich> findYeuThichByUserId(int userId);
    public YeuThich findYeuThichByUserIdAndIdSanBong(int userId, int idSanBong);
}
