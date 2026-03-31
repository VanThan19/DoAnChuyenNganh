package com.vanthan.supper.trungtam.repository;

import com.vanthan.supper.trungtam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,String> {
}
