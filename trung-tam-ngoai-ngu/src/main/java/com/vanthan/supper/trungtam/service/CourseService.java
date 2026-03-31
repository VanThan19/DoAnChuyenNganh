package com.vanthan.supper.trungtam.service;

import com.vanthan.supper.trungtam.entity.Course;
import com.vanthan.supper.trungtam.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }
    public Course getCourseById(String id) {
        return courseRepo.findById(id).orElse(null);
    }
}
