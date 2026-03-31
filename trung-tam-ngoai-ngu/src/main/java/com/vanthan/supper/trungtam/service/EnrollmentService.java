package com.vanthan.supper.trungtam.service;

import com.vanthan.supper.trungtam.entity.Course;
import com.vanthan.supper.trungtam.entity.Enrollment;
import com.vanthan.supper.trungtam.entity.Student;
import com.vanthan.supper.trungtam.entity.enums.EnrollmentStatus;
import com.vanthan.supper.trungtam.repository.CourseRepo;
import com.vanthan.supper.trungtam.repository.EnrollmentRepo;
import com.vanthan.supper.trungtam.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private ClassService classService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;


    public Enrollment getEnrollment(String studentId, String courseId) {
        return enrollmentRepo
                .findFirstByStudent_IdAndCourse_IdOrderByIdDesc(studentId, courseId)
                .orElse(null); // Câu lệnh này tìm bản ghi đăng ký mới nhất của sinh viên đó trong khóa học đó.
    }

    @Transactional
    public void markAsPaid(String studentId, String courseId) {

        //có nhiệm vụ cập nhật trạng thái đã thanh toán cho một bản ghi đăng ký khóa học
        Enrollment e = enrollmentRepo
                .findFirstByStudent_IdAndCourse_IdOrderByIdDesc(studentId, courseId)
                .orElseThrow();

        e.setStatus(EnrollmentStatus.PAID);
    }

    @Transactional
    public Enrollment createPending(String studentId, String courseId) {
// "Đăng ký khóa học". Nó đảm bảo rằng mỗi sinh viên chỉ có một bản ghi đăng ký cho một khóa học cụ thể và tránh tạo trùng lặp.
        // Nếu thấy đã đăng ký rồi -> Trả về bản ghi cũ (Người dùng không thấy lỗi, chỉ thấy nút thanh toán hiện ra).
        Optional<Enrollment> exist =
                enrollmentRepo.findFirstByStudent_IdAndCourse_IdOrderByIdDesc(studentId, courseId);

        if (exist.isPresent()) return exist.get();

        Enrollment e = new Enrollment();
        e.setStudent(studentRepo.findById(studentId).orElseThrow());
        e.setCourse(courseRepo.findById(courseId).orElseThrow());
        e.setStatus(EnrollmentStatus.PENDING);

        return enrollmentRepo.save(e);
    }
}
