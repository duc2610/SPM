package com.swp391.SPM.repository;

import com.swp391.SPM.dto.BookingDetailDTO;
import com.swp391.SPM.entity.SanBong;
import com.swp391.SPM.entity.YeuCauThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface YeuCauThueRepository extends JpaRepository<YeuCauThue, Integer> {
    List<YeuCauThue> findYeuCauThueByUserId(int userId);
    YeuCauThue findYeuCauThueById(int idYeuCauThue);
    YeuCauThue findYeuCauThueByIdSanBongAndAndIdGioHoatDong(int idSanBong, int idGioHoatDong);

}
