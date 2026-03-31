package com.vanthan.supper.trungtam.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Teacher")
public class Teacher {
    @Id
    @Column(name = "Id", columnDefinition = "CHAR(5)")
    private String id;

    @Column(name = "FullName", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String fullName;

    @Column(name = "Email", length = 100, nullable = false)
    private String email;

    @Column(name = "Phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "Specialization", columnDefinition = "NVARCHAR(150)")
    private String specialization;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CenterClass> centerClassList = new ArrayList<>();

    public List<CenterClass> getCenterClassList() {
        return centerClassList;
    }

    public void setCenterClassList(List<CenterClass> centerClassList) {
        this.centerClassList = centerClassList;
    }

    public Teacher() {
    }

    public Teacher(String id, String fullName, String email, String phone, String specialization) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
