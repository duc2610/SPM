package com.swp391.SPM.service;

import com.swp391.SPM.entity.SanBong;
import com.swp391.SPM.repository.SanBongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanBongServiceImpl implements SanBongService{
    @Autowired
    SanBongRepository sanBongRepository;
    @Override
    public SanBong getSanBongById(int sanBongId) {
        return sanBongRepository.findSanBongsById(sanBongId);
    }

    @Override
    public List<SanBong> getAllSanBong() {
        return sanBongRepository.findAll();
    }

    @Override
    public void deleteSanBongById(int idSanBong) {
        SanBong sanBong = sanBongRepository.findSanBongsById(idSanBong);
        sanBongRepository.delete(sanBong);
    }

    @Override
    public List<SanBong> searchByName(String keyword) {
        return this.sanBongRepository.searchByName(keyword);
    }

    @Override
    public Page<SanBong> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 6);
        return this.sanBongRepository.findAll(pageable);
    }

    @Override
    public Page<SanBong> searchByName(String keyword, Integer pageNo) {
        List list = this.searchByName(keyword);

        Pageable pageable  = PageRequest.of(pageNo-1, 6);
        Integer start = (int) pageable.getOffset();
        Integer end = (pageable.getOffset() +pageable.getPageSize()) > list.size() ? list.size() : (int) (pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<SanBong>(list, pageable, this.searchByName(keyword).size());
    }

    @Override
    public Page<SanBong> getSanBongByIdLoaiSan(int idLoaiSan, Integer pageNo) {
        List list = this.sanBongRepository.getSanBongByIdLoaiSan(idLoaiSan);
        Pageable pageable = PageRequest.of(pageNo-1, 6);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<SanBong>(list, pageable, this.sanBongRepository.getSanBongByIdLoaiSan(idLoaiSan).size());
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            this.sanBongRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(SanBong sanBong) {
        try{
            this.sanBongRepository.save(sanBong);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean create(SanBong sanBong) {
        try{
            this.sanBongRepository.save(sanBong);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
