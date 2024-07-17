package com.swp391.SPM.service;

import com.swp391.SPM.entity.Slot;

import java.util.List;

public interface SlotService {
     List<Slot> viewSlotByIdGioHoatDong(int idGioHoatDong);
     void saveSlot(Slot slot);
     void deleteSlot(Slot slot);
     Slot findSlotByIdGioHoatDongAndIdSanBong(int idGioHoatDOng, int idSanBong);
}
