package com.swp391.SPM.controller;

import com.swp391.SPM.dto.BinhLuanDTO;
import com.swp391.SPM.entity.*;
import com.swp391.SPM.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/view")
public class SanBongController {
    @Autowired
    SanBongService sanBongService;
    @Autowired
    YeuThichService yeuThichService;
    @Autowired
    UserService userService;
    @Autowired
    BinhLuanService binhLuanService;
    @Autowired
    GioHoatDongService gioHoatDongService;
    @Autowired
    SlotService slotService;
    @Autowired
    YeuCauThueService yeuCauThueService;

    @GetMapping("/san-bong-detail")
    public String viewSanBongDetail(@RequestParam(value = "idSanBong") Integer idSanBong,
                                    @RequestParam(value = "email") String email,
                                    Model model, HttpSession session) {
        if (!email.equals("anonymousUser")) {
            YeuThich yeuThich = yeuThichService.getYeuThich(userService.getUserByEmail(email).getId(), idSanBong);
            if (yeuThich != null) {
                int index = 1;
                model.addAttribute("index", index);
            }
            if (userService.getUserByEmail(email).getRoles().getId() != 1) {
                model.addAttribute("roleId", userService.getUserByEmail(email).getRoles().getId());
            }
        }
        if (session.getAttribute("temp") != null) {
            model.addAttribute("message", "Xin lỗi, khung giờ này đã được đặt từ trước!");
            session.removeAttribute("temp");
        }
        SanBong sanBong = sanBongService.getSanBongById(idSanBong);
        List<BinhLuan> binhLuanList = binhLuanService.getAllBinhLuan(idSanBong);
        List<BinhLuanDTO> binhLuanDTOList = new ArrayList<>();
        for (BinhLuan bl : binhLuanList) {
            BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
            binhLuanDTO.setIdBinhLuan(bl.getId());
            binhLuanDTO.setNoiDung(bl.getNoiDung());
            binhLuanDTO.setUser(userService.getUserById(bl.getUserId()));
            binhLuanDTO.setCreateDate(bl.getCreateDate());
            binhLuanDTOList.add(binhLuanDTO);
        }
        List<String> nextSevenDays = new ArrayList<>();
        LocalDate today = LocalDate.now().plusDays(1);;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd/MM");

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            nextSevenDays.add(date.format(formatter));
        }
        // in ra lịch đá của từng sân
        List<GioHoatDong> gioHoatDongAll = gioHoatDongService.getAllGioHoatDong();
        List<GioHoatDong> gioHoatDong = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            gioHoatDong.add(gioHoatDongAll.get(i));
        }
        model.addAttribute("gioHoatDong", gioHoatDong);
        model.addAttribute("nextSevenDays", nextSevenDays);
        model.addAttribute("binhLuanList", binhLuanDTOList);
        model.addAttribute("sanBong", sanBong);
        model.addAttribute("emailTemp", email);
        model.addAttribute("binhLuan", new BinhLuan());
        return "blog-details";
    }

    @GetMapping("/editComment")
    public String editBinhLuan(@RequestParam(value = "idComment") Integer idComment,
                               @RequestParam(value = "textComment") String textComment, Model model) {
        BinhLuan binhLuan = binhLuanService.getBinhLuanById(idComment);
        binhLuan.setNoiDung(textComment);
        binhLuanService.saveBinhLuan(binhLuan);
        String email = userService.getUserById(binhLuan.getUserId()).getEmail();
        int idSanBong = sanBongService.getSanBongById(binhLuan.getIdSan()).getId();
        return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBong;
    }

    @PostMapping("/themBinhLuan")
    public String addBinhLuan(@ModelAttribute(value = "binhLuan") BinhLuan binhLuan, @RequestParam(value = "idSanBong") Integer idSanBong,
                              @RequestParam(value = "email") String email, Model model) {
        User user = userService.getUserByEmail(email);
        binhLuan.setIdSan(idSanBong);
        binhLuan.setUserId(user.getId());
        binhLuan.setCreateDate(new Date());
        binhLuanService.saveBinhLuan(binhLuan);
        return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBong;
    }

    @GetMapping("/deleteBinhLuan")
    public String deleteBinhLuan(@RequestParam(value = "idBinhLuan") Integer idBinhLuan, @RequestParam(value = "idSanBong") Integer idSanBong,
                                 @RequestParam(value = "email") String email, Model model) {
        BinhLuan binhLuan = binhLuanService.getBinhLuanById(idBinhLuan);
        binhLuanService.deleteBinhLuan(binhLuan);
        return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBong;
    }
}
