package com.swp391.SPM.controller;

import com.swp391.SPM.entity.*;
import com.swp391.SPM.security.vnpConfig.VNPayService;
import com.swp391.SPM.service.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dat-san")
public class DatSanController {
    @Autowired
    UserService userService;
    @Autowired
    SanBongService sanBongService;
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    YeuCauThueService yeuCauThueService;
    @Autowired
    SlotService slotService;
    @Autowired
    GioHoatDongService gioHoatDongService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/view")
    public String thongTinDatSan(@RequestParam(value = "email") String email, @RequestParam(value = "date") String date,
                                 @RequestParam(value = "idSanBong") Integer idSanBong, @RequestParam(value = "gioDa") String gioDa,
                                 Model model, HttpSession session) {
        User user = userService.getUserByEmail(email);
        SanBong sanBong = sanBongService.getSanBongById(idSanBong);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(gioDa, timeFormatter);
        Time thoiGianDa = Time.valueOf(time);
        GioHoatDong gioHoatDong = gioHoatDongService.findGioHoatDongByNgayDaAndGioDa(date, thoiGianDa);
        if(gioHoatDong != null){
            YeuCauThue yeuCauThue = yeuCauThueService.findYeuCauThueByIdSanBongAndAndIdGioHoatDong(idSanBong, gioHoatDong.getId());
            if(yeuCauThue != null){
                session.setAttribute("temp", "temp");
                return "redirect:/view/san-bong-detail?email=" + email + "&idSanBong=" + idSanBong;
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("sanBong", sanBong);
        model.addAttribute("date", date);
        model.addAttribute("gioDa", gioDa);
        return "Booking";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan(@RequestParam(value = "email") String email, @RequestParam(value = "date") String date,
                            @RequestParam(value = "idSanBong") Integer idSanBong, @RequestParam(value = "gioDa") String gioDa,
                            HttpSession session, HttpServletRequest request) {
        SanBong sanBong = sanBongService.getSanBongById(idSanBong);
        session.setAttribute("idSanBong", idSanBong);
        session.setAttribute("email", email);
        session.setAttribute("gioDa", gioDa);
        session.setAttribute("date", date);

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(sanBong.getTienSan()*20/100, "Thanh toan dat san", baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model, HttpSession session) throws ParseException, MessagingException {
        int paymentStatus = vnPayService.orderReturn(request);

        // convert date
        String ngayDa = (String) session.getAttribute("date");
        String timeString = (String) session.getAttribute("gioDa");
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);
        int idSanBong = (int) session.getAttribute("idSanBong");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(timeString, timeFormatter);
        Time thoiGianDa = Time.valueOf(time);
        SanBong sanBong = sanBongService.getSanBongById(idSanBong);
        // Format the LocalDate object to the desired output format
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        int totalPrice = Integer.parseInt(request.getParameter("vnp_Amount"));

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", (totalPrice / 100) + "");
        model.addAttribute("paymentTime", new Date());
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("idSanBong", idSanBong);
        Context context = new Context();
        context.setVariable("tenSanBong", sanBong.getTenSanBong());
        context.setVariable("giaSan", sanBong.getTienSan()*0.2);
        context.setVariable("ngayDa", ngayDa);
        context.setVariable("gioDa", thoiGianDa);
        context.setVariable("diaChi", sanBong.getDiaDiem());
        if (paymentStatus == 1) {
            List<GioHoatDong> gioHoatDong = gioHoatDongService.getAllGioHoatDong();
            boolean check = true;
            for (int j = 0; j < gioHoatDong.size(); j++) {
                if (gioHoatDong.get(j).getGioDa().compareTo(thoiGianDa) == 0
                        && gioHoatDong.get(j).getNgayDa().equals(ngayDa)) {
                   check = false;
                   break;
                }
            }
            if(check){
                gioHoatDongService.saveGioHoatDong(new GioHoatDong(thoiGianDa, ngayDa));
            }
            GioHoatDong gioHoatDong1 = gioHoatDongService.findGioHoatDongByNgayDaAndGioDa(ngayDa, thoiGianDa);
            slotService.saveSlot(new Slot(idSanBong, gioHoatDong1.getId(), true));
            YeuCauThue yeuCauThue = new YeuCauThue(user.getId(), idSanBong, new Date(), gioHoatDong1.getId());
            yeuCauThueService.saveYeuCauThue(yeuCauThue);
            emailService.sendEmail(user.getEmail(), "Xác nhận thông tin đặt sân ", "mailXacNhan", context);
            return "ordersuccess";
        }
        return "orderfail";
    }
}