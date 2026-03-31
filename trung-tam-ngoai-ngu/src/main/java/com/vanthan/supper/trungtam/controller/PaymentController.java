package com.vanthan.supper.trungtam.controller;

import com.vanthan.supper.trungtam.entity.*;
import com.vanthan.supper.trungtam.service.CourseService;
import com.vanthan.supper.trungtam.service.EnrollmentService;
import com.vanthan.supper.trungtam.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaymentController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private PaymentService paymentService;

    ////Step 1: tạo payment + redirect VNPay
    @GetMapping("/payment/{courseId}")
    public String pay(@PathVariable String courseId, HttpSession session) {

        Account user = (Account) session.getAttribute("user");

        Student student = user.getStudent();

        Enrollment enrollment = enrollmentService
                .getEnrollment(student.getId(), courseId);

        // 🔥 tạo payment
        Payment payment = paymentService.createPayment(enrollment);

        // tạo URL VNPay
        String url = paymentService.createVNPayUrl(
                courseId,
                payment.getAmount().toString(),
                payment.getId().toString() // transaction
        );

        return "redirect:" + url;
    }
    @PostMapping("/payment/confirm")
    public String confirmPayment(@RequestParam String courseId,
                                 HttpSession session) {

        Account user = (Account) session.getAttribute("user");

        enrollmentService.markAsPaid(user.getStudent().getId(), courseId);

        return "redirect:/course/detail/" + courseId;
    }
    @GetMapping("/payment/vnpay-return")
    public String paymentReturn(HttpServletRequest request) {

        String responseCode = request.getParameter("vnp_ResponseCode");
        String txnRef = request.getParameter("vnp_TxnRef");

        if ("00".equals(responseCode)) {

            // thanh toán thành công
            paymentService.successPayment(txnRef, null, null);

            return "redirect:/learning/success";
        }

        return "redirect:/payment/fail";
    }


}
