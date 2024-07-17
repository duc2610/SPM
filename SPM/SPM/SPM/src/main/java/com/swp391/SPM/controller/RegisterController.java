package com.swp391.SPM.controller;

import com.swp391.SPM.entity.Role;
import com.swp391.SPM.entity.User;
import com.swp391.SPM.handle.RegisterUser;
import com.swp391.SPM.repository.RoleRepository;
import com.swp391.SPM.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    public UserService userService;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model){
        RegisterUser ru = new RegisterUser();
        model.addAttribute("registerUser", ru);
        return "register";

    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute RegisterUser registerUser, BindingResult result, Model model, HttpSession session){
        String email = registerUser.getEmail();

        // register validation
        if (result.hasErrors()){
            return "register";
        }
        //Kiểm tra tồn tại chưa

        User useExisting = userService.getUserByEmail(email);
        if (useExisting !=null){
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("my_error", "Tài khoản đã tồn tại!!!");
            return "register";
        }

        //Done
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setFullName(registerUser.getFullname());
        user.setPhoneNumber(registerUser.getPhone());
        Role role = new Role();
        role.setId(1);
        user.setRoles(role);
        user.setLogType("Normal");
        userService.save(user);
        //Thông báo success
        session.setAttribute("user", user);

        return "login";
    }
}