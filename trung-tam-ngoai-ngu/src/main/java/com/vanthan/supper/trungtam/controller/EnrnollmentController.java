package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.Account;
import com.vanthan.supper.trungtam.entity.Course;
import com.vanthan.supper.trungtam.entity.Student;
import com.vanthan.supper.trungtam.service.CourseService;
import com.vanthan.supper.trungtam.service.EnrollmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EnrnollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/enroll/auto")
    public String autoEnroll(@RequestParam String courseId,
                             HttpSession session,
                             RedirectAttributes redirect) {

        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        try {
            enrollmentService.createPending(user.getStudent().getId(), courseId);
            redirect.addFlashAttribute("success", "Đăng ký thành công, vui lòng thanh toán!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/course/detail/" + courseId;
    }
}
