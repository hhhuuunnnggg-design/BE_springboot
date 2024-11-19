package com.example.demo.crud;

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

import com.example.demo.Model.Student;
import com.example.demo.Service.IstudentService;
import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("students")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentController {

    IstudentService studentService;

    // ----------------------------------------------------------
    // tìm kiếm theo tên và sđt, ngày
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Student>>> getSearchStudent(
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "phone", defaultValue = "", required = false) String phone,
            @RequestParam(value = "gender", defaultValue = "", required = false) String gender,
            @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
            @RequestParam(value = "endDate", defaultValue = "", required = false) String endDate) {
        List<Student> foundStudents = studentService.searchStudents(name, phone, startDate, endDate, gender);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder().data(foundStudents).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    // Xóa sinh viên theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
        ApiResponse<Student> successResponse = new ApiResponse<>(200, "đã xóa thành công", null);
        return ResponseEntity.ok(successResponse);
    }

    // Cập nhật thông tin sinh viên theo id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable int id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentById(id, student);
        if (updatedStudent == null) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        ApiResponse<Student> response = new ApiResponse<>(200, "đã cập nhật thành công", updatedStudent);
        return ResponseEntity.ok(response);
    }

    // Tìm kiếm sinh viên theo id
    @GetMapping("/searchId/{id}")
    public ResponseEntity<ApiResponse<Student>> getSearchStudentById(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            // exception (lỗi)
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder().data(student).build());
    }

    // thêm sinh viên
    @PostMapping("")
    public ResponseEntity<ApiResponse<Student>> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder().data(studentService.save(student)).build());

    }

}
