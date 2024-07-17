package com.swp391.SPM.repository;

import com.swp391.SPM.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
     List<Slot> findSlotByIdGioHoatDong(int idGioHoatDong);
     Slot findSlotByIdGioHoatDongAndIdSanBong(int idGioHoatDong, int idSanBong);
}
