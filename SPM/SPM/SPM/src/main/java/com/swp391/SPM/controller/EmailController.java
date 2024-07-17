package com.swp391.SPM.controller;


import com.swp391.SPM.dto.CheckForm;
import com.swp391.SPM.dto.EmailForm;
import com.swp391.SPM.entity.User;
import com.swp391.SPM.service.EmailService;
import com.swp391.SPM.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/forgetPassword")
    public String showEmailForm(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        return "forget-password";
    }

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomNumber() {
        int number = random.nextInt(900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999
        return String.valueOf(number);
    }
    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute EmailForm emailForm, Model model, HttpSession session) {
        Context context = new Context();
        String randomNumber = generateRandomNumber();
        context.setVariable("randomNumber", randomNumber);
        // Lưu số ngẫu nhiên và thời gian hết hạn vào session
        session.setAttribute("randomNumber", randomNumber);
        session.setAttribute("expiryTime", LocalDateTime.now().plusSeconds(120));
        session.setAttribute("email", emailForm.getTo());
        try {
            if(userService.getUserByEmail(emailForm.getTo())==null){
                model.addAttribute("message", "Email không tồn tại trong cơ sở dữ liệu");
                return "forget-password";
            }
            else if(userService.getUserByEmail(emailForm.getTo()).getLogType().equals("GG")){
                model.addAttribute("message", "Email này dùng đã để đăng nhập bằng GG");
                return "forget-password";
            }
            emailService.sendEmail(emailForm.getTo(), "Mã xác thực", "email", context);
        } catch (Exception e) {
        }
        model.addAttribute("checkForm", new CheckForm());
        return "checkNumber";
    }
    @PostMapping("/checkNumber")
    public String checkNumber(@ModelAttribute CheckForm checkForm, Model model, HttpSession session) {
        String sessionRandomNumber = (String) session.getAttribute("randomNumber");
        LocalDateTime expiryTime = (LocalDateTime) session.getAttribute("expiryTime");

        if (expiryTime != null && LocalDateTime.now().isAfter(expiryTime)) {
            model.addAttribute("message1", "Thất bại, mã xác thực đã hết hạn, vui lòng gửi lại mail!");
            return "checkNumber";
        } else if (sessionRandomNumber != null && sessionRandomNumber.equals(checkForm.getRandomNumber())) {
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("user", new User());
            return "reset-password";
        } else {
            model.addAttribute("message", "Thất bại, mã xác thực không đúng!");
            return "checkNumber";
        }

    }
    @PostMapping("resetPassword")
    public String resetPassword(@ModelAttribute User user, Model model, HttpSession session){
        if(!user.getPassword().matches("^(?=.*\\d)(?=.*[@#$%^&+=!]).*$")){
            model.addAttribute("message", "Mật khẩu phải có ít nhất một chữ số và một ký tự đặc biệt");
            model.addAttribute("email", session.getAttribute("email"));
            return "reset-password";
        }
        if (user.getPassword().length()<8){
            model.addAttribute("message", "Độ dài tối thiểu là 8");
            model.addAttribute("email", session.getAttribute("email"));
            return "reset-password";
        }
        User u = userService.getUserByEmail((String) session.getAttribute("email"));
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(u);
        model.addAttribute("message", "Thay đổi mật khẩu thành công!");
        model.addAttribute("email", session.getAttribute("email"));
        return "reset-password";
    }
}

