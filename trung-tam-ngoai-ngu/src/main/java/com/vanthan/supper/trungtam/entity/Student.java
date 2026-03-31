package com.vanthan.supper.trungtam.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "Id", columnDefinition = "CHAR(5)")
    private String id;
    @Column(name = "FullName", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String fullName;
    @Column(name = "Phone", columnDefinition = "VARCHAR(15)" , nullable = false)
    private String phone;
    @Column(name = "Email", length = 100, nullable = false)
    private String email;
    @Column(name = "Address", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String address;
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<Enrollment> listEnrollments = new ArrayList<>();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Student() {
    }

    public Student(String id, String fullName, String phone, String email, String address, LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
