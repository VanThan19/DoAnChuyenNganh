package com.vanthan.supper.trungtam.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(5)")
    private String id; // Ví dụ: C0001, C0002

    @Column(name = "course_name", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String courseName;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "image")
    private String image; // Lưu đường dẫn ảnh hoặc URL ảnh khóa học

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price; // Dùng BigDecimal để quản lý tiền tệ chính xác

    @Column(name = "level", length = 50)
    private String level; // Ví dụ: IELTS 6.5, TOEIC 500, Beginner...

    @Column(name = "duration")
    private Integer duration; // Thời lượng (ví dụ: số tiết hoặc số tháng)

    @Column(name = "roadmap", columnDefinition = "NVARCHAR(MAX)")
    private String roadmap;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CenterClass> centerClassList = new ArrayList<>();

    public List<CenterClass> getCenterClassList() {
        return centerClassList;
    }

    public void setCenterClassList(List<CenterClass> centerClassList) {
        this.centerClassList = centerClassList;
    }

    public String getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(String roadmap) {
        this.roadmap = roadmap;
    }

    public Course() {
    }

    public Course(String id, String courseName, String description, String image, BigDecimal price, String level, Integer duration) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.level = level;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", level='" + level + '\'' +
                ", duration=" + duration +
                '}';
    }
}
