create database TrungTamNgoaiNgu 
use TrungTamNgoaiNgu 

select * from Account 

CREATE TABLE Account (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    FullName NVARCHAR(250) NOT NULL,
    Role VARCHAR(50) NOT NULL,
    Status BIT NOT NULL DEFAULT 1,
    CreatedAt DATETIME2 NOT NULL DEFAULT SYSDATETIME()
);
INSERT INTO Account (Username, Password, Email, FullName, Role)
VALUES 
('vanthan', '123', 'than@gmail.com', N'Nguyễn Văn Thân', 'Student'),
('minhthang', '123', 'thang@gmail.com', N'Hoàng Minh Thắng', 'Student'),
('thayphong', '123', 'thayphong@gmail.com', N'Phan Anh Phong', 'Teacher'),
('trongmanh', '123', 'manh@gmail.com', N'Nguyễn Trọng Mạnh', 'Teacher'),
('admin1', '123', 'admin1@gmail.com', N'Admin Tổng', 'Admin');



CREATE TABLE Student (
    Id CHAR(5) PRIMARY KEY,
    FullName NVARCHAR(250) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Address NVARCHAR(250) NOT NULL,
    Birthday DATE NULL,
    accountId BIGINT NULL UNIQUE,
    CONSTRAINT FK_Student_Account
        FOREIGN KEY (accountId) REFERENCES Account(Id)
);

INSERT INTO Student (Id, FullName, Phone, Email, Address, Birthday, accountId)
VALUES
('S0001', N'Nguyễn Văn Thân', '0900000001', 'than@gmail.com', N'Hà Nội', '2005-06-19', 1),
('S0002', N'Hoàng Minh Thắng', '0900000002', 'thang@gmail.com', N'HCM', '2001-02-02', 2)

CREATE TABLE Teacher (
    Id CHAR(5) PRIMARY KEY,
    FullName NVARCHAR(250) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Specialization NVARCHAR(150) NULL,
    AccountId BIGINT NULL UNIQUE,
    CONSTRAINT FK_Teacher_Account
        FOREIGN KEY (AccountId) REFERENCES Account(Id)
);

INSERT INTO Teacher (Id, FullName, Email, Phone, Specialization, AccountId)
VALUES
('T0001', N'Phan Anh Phong', 'thayphong@gmail.com', '0911111111', N'Lập trình Java', 3),
('T0002', N'Nguyễn Trọng Mạnh', 'manh@gmail.com', '0922222222', N'Frontend React', 4)

CREATE TABLE Course (
    id CHAR(5) PRIMARY KEY,
    course_name NVARCHAR(250) NOT NULL,
    description NVARCHAR(MAX) NULL,
    image VARCHAR(255) NULL,
    price DECIMAL(15,2) NULL,
    level VARCHAR(50) NULL,
    duration INT NULL,
    roadmap NVARCHAR(MAX) NULL
);

INSERT INTO Course (id, course_name, description, image, price, level, duration, roadmap)
VALUES 
('C0001', N'Khóa học IELTS 6.5', N'Luyện thi IELTS từ 5.0 lên 6.5', 'ielts65.jpg', 3500000.00, 'IELTS 6.5', 3, N'
<li><strong>Giai đoạn 1:</strong> Củng cố ngữ pháp cơ bản & phát âm chuẩn (4 tuần).</li>
<li><strong>Giai đoạn 2:</strong> Làm quen dạng bài Listening & Reading IELTS (4 tuần).</li>
<li><strong>Giai đoạn 3:</strong> Phát triển kỹ năng Speaking & Writing theo chủ đề (4 tuần).</li>
<li><strong>Giai đoạn 4:</strong> Luyện đề & Mock Test sát đề thi thật (2 tuần).</li>
'),

('C0002', N'Khóa học TOEIC 500', N'Đạt mục tiêu TOEIC 500+', 'toeic500.jpg', 2500000.00, 'TOEIC 500', 2,N'
<li><strong>Giai đoạn 1:</strong> Nắm vững từ vựng & ngữ pháp TOEIC cơ bản (3 tuần).</li>
<li><strong>Giai đoạn 2:</strong> Luyện kỹ năng Listening theo từng Part (3 tuần).</li>
<li><strong>Giai đoạn 3:</strong> Làm quen dạng bài Reading & chiến thuật làm bài (3 tuần).</li>
<li><strong>Giai đoạn 4:</strong> Luyện đề tổng hợp & tăng tốc độ làm bài (1-2 tuần).</li>
'),

('C0003', N'Tiếng Anh giao tiếp cơ bản', N'Khóa học cho người mới bắt đầu', 'basic.jpg', 1800000.00, 'Beginner', 2, N'
<li><strong>Giai đoạn 1:</strong> Làm quen từ vựng & cấu trúc câu giao tiếp cơ bản (2 tuần).</li>
<li><strong>Giai đoạn 2:</strong> Luyện phát âm & phản xạ giao tiếp hàng ngày (2 tuần).</li>
<li><strong>Giai đoạn 3:</strong> Thực hành hội thoại theo tình huống thực tế (2 tuần).</li>
<li><strong>Giai đoạn 4:</strong> Tăng cường phản xạ & giao tiếp tự nhiên (2 tuần).</li>
'),

('C0004', N'IELTS 7.0 Intensive', N'Luyện đề chuyên sâu IELTS 7.0+', 'ielts70.jpg', 4500000.00, 'IELTS 7.0', 4,N'
<li><strong>Giai đoạn 1:</strong> Ôn tập nâng cao ngữ pháp & từ vựng học thuật (3 tuần).</li>
<li><strong>Giai đoạn 2:</strong> Luyện chuyên sâu Listening & Reading band 7.0+ (3 tuần).</li>
<li><strong>Giai đoạn 3:</strong> Nâng cao kỹ năng Writing Task 1 & 2 (3 tuần).</li>
<li><strong>Giai đoạn 4:</strong> Luyện Speaking chuyên sâu & Mock Test toàn diện (3 tuần).</li>
' ),

('C0005', N'Tiếng Anh cho trẻ em', N'Phát triển nền tảng tiếng Anh cho trẻ', 'kids.jpg', 1500000.00, 'Kids', 3,N'
<li><strong>Giai đoạn 1:</strong> Làm quen từ vựng qua hình ảnh & trò chơi (2 tuần).</li>
<li><strong>Giai đoạn 2:</strong> Học phát âm & nghe qua bài hát, video (2 tuần).</li>
<li><strong>Giai đoạn 3:</strong> Luyện nói câu đơn giản theo chủ đề quen thuộc (2 tuần).</li>
<li><strong>Giai đoạn 4:</strong> Phát triển phản xạ giao tiếp tự nhiên (2 tuần).</li>
');
--Khóa học (Course)
 --   ↓
--Mở nhiều lớp (CenterClass)
  --  ↓
--Học viên đăng ký (Enrollment)
   -- ↓
--Gán vào lớp cụ thể
CREATE TABLE CenterClass ( -- 1 Khóa học có nhiều lớp , 1 giáo viên dạy nhiều lớp , 1 lớp có nhiều học viên đăng ký ,
    Id CHAR(5) PRIMARY KEY,
    ClassName NVARCHAR(250) NOT NULL,
    RoomName NVARCHAR(100) NULL,
    Status VARCHAR(30) NOT NULL DEFAULT 'OPEN',
    CourseId CHAR(5) NOT NULL,
    TeacherId CHAR(5) NOT NULL,
    CONSTRAINT FK_CenterClass_Course
        FOREIGN KEY (CourseId) REFERENCES Course(id),
    CONSTRAINT FK_CenterClass_Teacher
        FOREIGN KEY (TeacherId) REFERENCES Teacher(Id)
);

CREATE TABLE Enrollment ( 
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,

    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',

    course_id CHAR(5) NOT NULL,
    class_id CHAR(5) NULL,
    student_id CHAR(5) NOT NULL,
    approved_by_teacher_id CHAR(5) NULL, --Có phân quyền teacher duyệt

    CONSTRAINT FK_Enrollment_Course
        FOREIGN KEY (course_id) REFERENCES Course(id),

    CONSTRAINT FK_Enrollment_CenterClass
        FOREIGN KEY (class_id) REFERENCES CenterClass(Id),

    CONSTRAINT FK_Enrollment_Student
        FOREIGN KEY (student_id) REFERENCES Student(Id),

    CONSTRAINT FK_Enrollment_ApprovedTeacher
        FOREIGN KEY (approved_by_teacher_id) REFERENCES Teacher(Id),

    CONSTRAINT CK_Enrollment_Status
        CHECK (status IN ('PENDING', 'APPROVED', 'ACTIVE', 'PAID', 'REJECTED')),

    CONSTRAINT UQ_Enrollment UNIQUE (student_id, course_id)
);
 --- Enrollment 1 - N Payment
 -- Course → Enrollment → Payment → VNPay → Callback → Update DB
 select * from Payment
CREATE TABLE Payment (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,

    enrollment_id BIGINT NOT NULL,

    amount DECIMAL(15,2) NOT NULL,

    method VARCHAR(50), -- VNPAY, MOMO, BANK

    status VARCHAR(30) NOT NULL, -- PENDING, SUCCESS, FAILED

    transaction_no VARCHAR(255), -- mã VNPay

    created_at DATETIME2 DEFAULT SYSDATETIME(),

    CONSTRAINT FK_Payment_Enrollment
        FOREIGN KEY (enrollment_id) REFERENCES Enrollment(Id)
);


--- chưa 

CREATE TABLE Schedule (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ClassId CHAR(5) NOT NULL,
    DayOfWeek VARCHAR(20) NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    RoomName NVARCHAR(100) NULL,
    CONSTRAINT FK_Schedule_CenterClass
        FOREIGN KEY (ClassId) REFERENCES CenterClass(Id)
);
CREATE TABLE Lesson (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,

    Title NVARCHAR(250) NOT NULL,
    Video_Url VARCHAR(MAX) NOT NULL,
    Order_Index INT NOT NULL,

    class_id CHAR(5) NOT NULL,

    CONSTRAINT FK_Lesson_Class
        FOREIGN KEY (class_id) REFERENCES CenterClass(Id)
);