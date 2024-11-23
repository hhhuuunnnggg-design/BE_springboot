use student_management;

CREATE TABLE Employee (
      id INT PRIMARY KEY AUTO_INCREMENT, -- ID là khóa chính, tự động tăng
      name VARCHAR(255) NOT NULL, -- Tên nhân viên
      code INT NOT NULL, -- Mã code
      numberPhone VARCHAR(15) NOT NULL, -- Số điện thoại
      birthDate DATE NOT NULL, -- Ngày sinh
      gender ENUM('Male', 'Female', 'Other') NOT NULL, -- Giới tính
      salary DECIMAL(15, 2) NOT NULL -- Lương (giá trị lớn và có 2 chữ số thập phân)
);

INSERT INTO Employee (id, name, code, numberPhone, birthDate, gender, salary) VALUES
  (1, 'Hùng', 10, '0213654215', '2000-01-01', 'Female', 4500000.00),
  (2, 'hùng', 8, '0787495636', '1999-02-15', 'Female', 7500000.00),
  (3, 'Huy', 10, '0213654219', '2001-06-20', 'Male', 15000000.00),
  (4, 'Duy', 5, '0395648952', '2002-11-10', 'Other', 9500000.00);

SELECT * FROM Employee;

-- ----------------------- bất dong san

USE bds;

-- Bảng phụ: Loại Mặt bằng
CREATE TABLE loaimatbang (
    loaiMatBangId INT PRIMARY KEY AUTO_INCREMENT, -- Khóa chính
    tenMatBang VARCHAR(255) NOT NULL             -- Tên loại mặt bằng
);

-- Bảng chính: Mặt bằng
CREATE TABLE matbang (
    id INT PRIMARY KEY AUTO_INCREMENT,            -- Khóa chính
    ten VARCHAR(255) NOT NULL,                    -- Tên mặt bằng
    diachi VARCHAR(255) NOT NULL,                 -- Địa chỉ
    dientich DOUBLE NOT NULL,                     -- Diện tích
    loaiMatBangId INT NOT NULL,                   -- Khóa ngoại (liên kết đến bảng loaimatbang)
    giathue DOUBLE NOT NULL,                      -- Giá thuê
    ngaythue DATE NOT NULL,                       -- Ngày bắt đầu cho thuê
    FOREIGN KEY (loaiMatBangId) REFERENCES loaimatbang(loaiMatBangId) -- Liên kết với bảng loaimatbang
);

INSERT INTO loaimatbang (tenMatBang) VALUES
('Nhà ở'),
('Văn phòng'),
('Kinh doanh');


INSERT INTO matbang (ten, diachi, dientich, loaiMatBangId, giathue, ngaythue) VALUES
('Mặt bằng 1', '123 Đường A, Quận 1', 120.5, 1, 15000000.00, '2023-11-01'),
('Mặt bằng 2', '456 Đường B, Quận 2', 80.0, 2, 20000000.00, '2023-10-15'),
('Mặt bằng 3', '789 Đường C, Quận 3', 200.0, 3, 30000000.00, '2023-12-01'),
('Mặt bằng 4', '101 Đường D, Quận 4', 150.5, 1, 18000000.00, '2023-09-10'),
('Mặt bằng 5', '202 Đường E, Quận 5', 250.0, 3, 40000000.00, '2023-08-25');
