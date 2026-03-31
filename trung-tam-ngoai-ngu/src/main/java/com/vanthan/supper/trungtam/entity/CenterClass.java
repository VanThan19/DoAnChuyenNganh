package com.vanthan.supper.trungtam.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CenterClass")
public class CenterClass {
    @Id
    @Column(name = "Id", columnDefinition = "CHAR(5)")
    private String id;
    @Column(name = "className", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String className;
    @Column(name = "RoomName", columnDefinition = "NVARCHAR(100)",nullable = false)
    private String roomName;
    @Column(name = "Status", length = 30, nullable = false)
    private String status = "OPEN";

    @ManyToOne
    @JoinColumn(name = "CourseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "TeacherId")
    private Teacher teacher;

    @OneToMany(mappedBy = "centerClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollmentList = new ArrayList<>();

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CenterClass() {
    }

    public CenterClass(String id, String className, String roomName, String status) {
        this.id = id;
        this.className = className;
        this.roomName = roomName;
        this.status = status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CenterClass{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", roomName='" + roomName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
