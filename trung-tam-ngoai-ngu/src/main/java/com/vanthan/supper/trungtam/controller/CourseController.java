package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.Account;
import com.vanthan.supper.trungtam.entity.Course;
import com.vanthan.supper.trungtam.entity.Enrollment;
import com.vanthan.supper.trungtam.entity.Student;
import com.vanthan.supper.trungtam.repository.CourseRepo;
import com.vanthan.supper.trungtam.repository.EnrollmentRepo;
import com.vanthan.supper.trungtam.service.CourseService;
import com.vanthan.supper.trungtam.service.EnrollmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private EnrollmentService enrollmentService;
    @GetMapping("/course/detail/{id}")
    public String showDetail(@PathVariable String id,
                             Model model,
                             HttpSession session) {

        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        Account user = (Account) session.getAttribute("user");

        String status = null;

        if (user != null && user.getStudent() != null) {

            String studentId = user.getStudent().getId();

            Enrollment enrollment = enrollmentService
                    .getEnrollment(studentId, id);

            if (enrollment != null) {
                status = enrollment.getStatus().name(); // 🔥 nếu dùng enum
                // hoặc:
                // status = enrollment.getStatus(); nếu String
            }
        }

        model.addAttribute("status", status);

        return "course-detail";
    }


}
