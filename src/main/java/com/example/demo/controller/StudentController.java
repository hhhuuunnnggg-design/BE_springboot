package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Employee;
import com.example.demo.Service.IstudentService;
import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("employees")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentController {

    IstudentService studentService;

    // Tìm kiếm sinh viên theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getSearchStudentById(@PathVariable("id") int id) {
        Employee student = studentService.findById(id);
        if (student == null) {
            // exception (lỗi)
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        return ResponseEntity.ok(ApiResponse.<Employee>builder().data(student).build());
    }

    // ----------------------------------------------------------
    // tìm kiếm theo tên và sđt, ngày
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Employee>>> getSearchStudent(
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "phone", defaultValue = "", required = false) String phone,
            @RequestParam(value = "gender", defaultValue = "", required = false) String gender,
            @RequestParam(value = "tienluong", defaultValue = "", required = false) String tienluong,
            @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
            @RequestParam(value = "endDate", defaultValue = "", required = false) String endDate) {
        List<Employee> foundStudents = studentService.searchStudents(name, phone, startDate, endDate, gender,
                tienluong);
        ApiResponse<List<Employee>> response = ApiResponse.<List<Employee>>builder().data(foundStudents).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<Employee> getAllStudents() {
        return studentService.findAll();
    }

    // Xóa sinh viên theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudentById(id);
        ApiResponse<Employee> successResponse = new ApiResponse<>(200, "đã xóa thành công", null);
        return ResponseEntity.ok(successResponse);
    }

    // Cập nhật thông tin sinh viên theo id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateStudentById(@PathVariable("id") int id,
            @RequestBody Employee student) {
        Employee updatedStudent = studentService.updateStudentById(id, student);
        if (updatedStudent == null) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        ApiResponse<Employee> response = new ApiResponse<>(200, "đã cập nhật thành công", updatedStudent);
        return ResponseEntity.ok(response);
    }

    // thêm sinh viên
    @PostMapping("")
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody Employee student) {
        if (student == null) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Employee>builder().data(studentService.save(student)).build());

    }

}
