package com.vanthan.supper.trungtam.repository;

import com.vanthan.supper.trungtam.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Long> {
    Optional<Enrollment> findFirstByStudent_IdAndCourse_IdOrderByIdDesc(String id, String id1);

    boolean existsByStudent_IdAndCourse_Id(String id, String id1);
}
