package com.swp391.SPM.repository;

import com.swp391.SPM.dto.CancellationRequestDTO;
import com.swp391.SPM.entity.YeuCauHuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface YeuCauHuyRepository extends JpaRepository<YeuCauHuy, Integer> {
}
