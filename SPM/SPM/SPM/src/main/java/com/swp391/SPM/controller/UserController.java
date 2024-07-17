package com.swp391.SPM.controller;

import com.swp391.SPM.dto.ChangePassword;
import com.swp391.SPM.dto.YeuCauThueDTO;
import com.swp391.SPM.entity.*;
import com.swp391.SPM.handle.CheckEditUser;
import com.swp391.SPM.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/view")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    YeuThichService yeuThichService;
    @Autowired
    SanBongService sanBongService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    YeuCauThueService yeuCauThueService;
    @Autowired
    GioHoatDongService gioHoatDongService;
    @Autowired
    SlotService slotService;
    @Autowired
    YeuCauHuyService yeuCauHuyService;
    @Autowired
    private PdfExportService pdfExportService;

    @GetMapping("/profile")
    public String viewProfile(@RequestParam(value = "email") String email, Model model, HttpSession session) throws ParseException {
        User user = userService.getUserByEmail(email);
        List<YeuThich> yeuThichList = yeuThichService.getListYeuThich(user.getId());
        List<SanBong> sanBongList = new ArrayList<>();
        for (YeuThich yeuThich: yeuThichList){
            sanBongList.add(sanBongService.getSanBongById(yeuThich.getIdSanBong()));
        }
        List<YeuCauThue> yeuCauThueList = yeuCauThueService.viewYeuCauThueByUserId(user.getId());
        List<YeuCauThueDTO> yeuCauThueDTOList = new ArrayList<>();
        if(!yeuCauThueList.isEmpty()){
            for(YeuCauThue yc : yeuCauThueList){
                SanBong sanBong = sanBongService.getSanBongById(yc.getIdSanBong());
                GioHoatDong gioHoatDong = gioHoatDongService.getGioHoatDongById(yc.getIdGioHoatDong());
                String[] parts = gioHoatDong.getNgayDa().split(" ");
                String ngayThang = parts[1]; // Lấy phần ngày tháng, ví dụ "02/07"
                // Thêm năm vào cuối chuỗi
                String[] dayMonthParts = ngayThang.split("/");
                String formattedDate = dayMonthParts[1] + "/" + dayMonthParts[0] + "/2024";
                // Định dạng cho chuỗi ngày
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                // Chuyển đổi chuỗi thành LocalDate
                LocalDate ngayDa = LocalDate.parse(formattedDate, formatter);
                YeuCauThueDTO yeuCauThueDTO = new YeuCauThueDTO(yc.getId(),sanBong.getTenSanBong(), ngayDa, gioHoatDong.getGioDa(),yc.getNgayTao(),sanBong.getTienSan()," Đã thanh toán");
                yeuCauThueDTOList.add(yeuCauThueDTO);
            }
        }
        LocalDate ngayHienTai = LocalDate.now();
        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = ngayHienTai.format(formatter);
        LocalDate localDate = LocalDate.parse(now, formatter);
        model.addAttribute("ngayHienTai", localDate);
        model.addAttribute("yeuCauThueDTOList", yeuCauThueDTOList);
        session.setAttribute("yeuCauThueDTOList", yeuCauThueDTOList);
        model.addAttribute("sanBongYeuThichList", sanBongList);
        model.addAttribute("yeuThichList", yeuThichList);
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        model.addAttribute("changePassword", new ChangePassword());
        if(!user.getLogType().equals("GG")){
            model.addAttribute("typeLog", "typeLog");
            session.setAttribute("typeLog", "typeLog");
        }
        return "profile";
    }


    @GetMapping("/delete-favorite-profile")
    public String deleteSanBongFavoritesProfile(@RequestParam(value = "idSanBongYeuThich") Integer idSanBongYeuThich,
                                         @RequestParam(value = "email") String email, Model model){
        User user = userService.getUserByEmail(email);
        yeuThichService.deleteSanBongYeuThich(user.getId(), idSanBongYeuThich);
        model.addAttribute("email", email);
        return "redirect:/view/profile?email=" + email;
    }

    @GetMapping("/delete-favorite-detail")
    public String deleteSanBongFavoritDetail(@RequestParam(value = "idSanBongYeuThich") Integer idSanBongYeuThich,
                                         @RequestParam(value = "email") String email, Model model){
        User user = userService.getUserByEmail(email);
        yeuThichService.deleteSanBongYeuThich(user.getId(), idSanBongYeuThich);
        model.addAttribute("email", email);
        return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBongYeuThich;
    }

    @GetMapping("/add-favorite")
    public String addSanBongFavorites(@RequestParam(value = "idSanBongYeuThich") Integer idSanBongYeuThich,
                                         @RequestParam(value = "email") String email, Model model){
        User user = userService.getUserByEmail(email);
        YeuThich yeuThich = new YeuThich();
        yeuThich.setUserId(user.getId());
        yeuThich.setIdSanBong(idSanBongYeuThich);
        yeuThichService.addYeuThichList(yeuThich);
        model.addAttribute("email", email);
        model.addAttribute("idSanBong", idSanBongYeuThich);
        return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBongYeuThich;
    }

    @PostMapping("/update_profile")
    public String updateProfile(@Valid @ModelAttribute("user") CheckEditUser checkEditUser, BindingResult result,
                                Model model, HttpSession session) {
       // User u = (User) session.getAttribute("user");
        if(result.hasErrors()){
            model.addAttribute("changePassword", new ChangePassword());
            return "profile";
        }
        User user = new User();
        user.setFullName(checkEditUser.getFullName());
        user.setEmail(checkEditUser.getEmail());
        user.setRoles(checkEditUser.getRoles());
        user.setPassword(checkEditUser.getPassword());
        user.setPhoneNumber(checkEditUser.getPhoneNumber());
        user.setId(checkEditUser.getId());
        user.setLogType(checkEditUser.getLogType());
        userService.update(user);
        return "redirect:/view/profile?email=" + user.getEmail();
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("changePassword") ChangePassword changePassword,
                                 HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("typeLog", session.getAttribute("typeLog"));
        System.out.println(user.getPassword());
        if(!passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())){
            model.addAttribute("message", "Mật khẩu cũ không đúng!");
            model.addAttribute("user", user);
            return "profile";
        }
        if(!changePassword.getNewPassword().equals(changePassword.getComfirmPassword())){
            model.addAttribute("message", "Mật khẩu xác nhận không trùng khớp!");
            model.addAttribute("user", user);
            return "profile";
        }
        if(changePassword.getNewPassword().equals(changePassword.getOldPassword())){
            model.addAttribute("message", "Mật khẩu mới đã trùng với mật khẩu cũ!");
            model.addAttribute("user", user);
            return "profile";
        }
        if(!changePassword.getNewPassword().matches("^(?=.*\\d)(?=.*[@#$%^&+=!]).*$")){
            model.addAttribute("message", "Mật khẩu phải có ít nhất một chữ số và một ký tự đặc biệt");
            model.addAttribute("user", user);
            return "profile";
        }
        if (changePassword.getNewPassword().length()<8){
            model.addAttribute("message", "Độ dài tối thiểu là 8");
            model.addAttribute("user", user);
            return "profile";
        }
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userService.save(user);
        model.addAttribute("message", "thay đổi mật khẩu thành công!");
        return "redirect:/view/profile?email=" + user.getEmail();
    }
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel(@RequestParam("id") Integer idYeuCauThue, HttpSession session) throws IOException {

        List<YeuCauThueDTO> bookings = (List<YeuCauThueDTO>) session.getAttribute("yeuCauThueDTOList");
        int temp = 0;
        for(YeuCauThueDTO yct: bookings){
            if(yct.getId() == idYeuCauThue){
                break;
            }
            temp++;
        }
        User user = (User) session.getAttribute("user");

        ByteArrayInputStream bis = pdfExportService.exportBookingsToPdf(bookings.get(temp), user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Hoa_Don.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    @PostMapping("/huy-san")
    public String huySanBong(@RequestParam("id") Integer idYeuCauthue,
                             @RequestParam("note") String note, Model model,
                             HttpSession session){
        User user = (User) session.getAttribute("user");
        YeuCauThue yeuCauThue = yeuCauThueService.findYeuCauThueById(idYeuCauthue);
        GioHoatDong gioHoatDong = gioHoatDongService.getGioHoatDongById(yeuCauThue.getIdGioHoatDong());
        yeuCauHuyService.saveYeuCauHuy(new YeuCauHuy(note, new Date(), user.getId(),
                gioHoatDong.getNgayDa(), gioHoatDong.getGioDa(), yeuCauThue.getIdSanBong()));

        Slot slot = slotService.findSlotByIdGioHoatDongAndIdSanBong(gioHoatDong.getId(), yeuCauThue.getIdSanBong());
        slotService.deleteSlot(slot);
        yeuCauThueService.deleteYeuCauThue(yeuCauThue);

        return "redirect:/view/profile?email=" + user.getEmail();
    }

}
