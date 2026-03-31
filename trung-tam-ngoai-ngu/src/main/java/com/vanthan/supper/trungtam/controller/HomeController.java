package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.Course;
import com.vanthan.supper.trungtam.repository.CourseRepo;
import com.vanthan.supper.trungtam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseService courseService;

    @GetMapping({"/", "/home"})
    public String showHomePage(Model model) {
        model.addAttribute("courses", courseService.getAllCourse());
        return "home";
    }
}
