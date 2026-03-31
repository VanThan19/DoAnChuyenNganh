package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.Account;
import com.vanthan.supper.trungtam.repository.AccountRepo;
import com.vanthan.supper.trungtam.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Model model, HttpSession session) throws Exception {
        try{

            Account acc = accountService.Authenticate(username,password);
            session.setAttribute("user",acc);
            String role = acc.getRole().toUpperCase();
            if (role.equals("ADMIN")) return "redirect:/admin/dashboard";
            if (role.equals("TEACHER")) return "redirect:/teacher/dashboard";
            return "redirect:/student";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";

        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // xóa toàn bộ session
        return "redirect:/login";
    }

}
