package com.swp391.SPM.service;

import com.swp391.SPM.entity.Slot;
import com.swp391.SPM.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements SlotService{
    @Autowired
    private SlotRepository slotRepository;

    @Override
    public List<Slot> viewSlotByIdGioHoatDong(int idGIoHoatDong) {
        return slotRepository.findSlotByIdGioHoatDong(idGIoHoatDong);
    }

    @Override
    public void saveSlot(Slot slot) {
        slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Slot slot) {
        slotRepository.delete(slot);
    }

    @Override
    public Slot findSlotByIdGioHoatDongAndIdSanBong(int idGioHoatDOng, int idSanBong) {
        return slotRepository.findSlotByIdGioHoatDongAndIdSanBong(idGioHoatDOng, idSanBong);
    }

}
