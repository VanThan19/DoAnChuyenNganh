package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.Account;
import com.vanthan.supper.trungtam.repository.CourseRepo;
import com.vanthan.supper.trungtam.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class StudentController {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseService courseService;
    @GetMapping("/student")
    public String studentHome(HttpSession session,Model model) {

        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (!user.getRole().equalsIgnoreCase("STUDENT")) {

            return "redirect:/home";
        }
        model.addAttribute("courses", courseService.getAllCourse());
        return "home";
    }



}
