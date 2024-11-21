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
