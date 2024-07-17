package com.swp391.SPM.controller;

import com.swp391.SPM.entity.LoaiSan;
import com.swp391.SPM.entity.SanBong;
import com.swp391.SPM.service.LoaiSanService;
import com.swp391.SPM.service.SanBongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/spm")
public class HomeController {
    @Autowired
    SanBongService sanBongService;
    @Autowired
    LoaiSanService loaiSanService;

    @GetMapping("/home")
    public String test(Model model, @Param("keyword") String keyword,
                       @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo){
        keyword = keyword == null ? keyword : keyword.trim();
        Page<SanBong> list = this.sanBongService.getAll(pageNo);
        List<LoaiSan> loaiSanList = loaiSanService.getAllLoaiSan();
        if(keyword!= null){
            list = this.sanBongService.searchByName(keyword,pageNo);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("sanBongList", list);
        model.addAttribute("loaiSanList", loaiSanList);
        return "blog-classic-grid";
    }

    @GetMapping("/view-pitch")
    public String getLoaiSan(@RequestParam(name = "idLoaiSan") Integer idLoaiSan, Model model,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        List<LoaiSan> loaiSanList = loaiSanService.getAllLoaiSan();
        Page<SanBong> sanBongList = sanBongService.getSanBongByIdLoaiSan(idLoaiSan, pageNo);
        model.addAttribute("sanBongList", sanBongList);
        model.addAttribute("loaiSanList", loaiSanList);
        model.addAttribute("totalPage", sanBongList.getTotalPages());
        model.addAttribute("currentPage", pageNo );
        return "blog-classic-grid";
    }

}
