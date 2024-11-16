package com.example.demo.crud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("students")
@RestController
public class studentController {
    private List<Students> students = new ArrayList<>(Arrays.asList(
            new Students(1, "Hùng", 10, "0213654215", LocalDate.parse("2000-01-01")),
            new Students(2, "hùng", 8, "0787495636", LocalDate.parse("1999-02-15")),
            new Students(3, "Huy", 10, "0213654219", LocalDate.parse("2001-06-20")),
            new Students(4, "Duy", 5, "0395648952", LocalDate.parse("2002-11-10"))
    ));

    // view
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Students>>> getStudents() {
        ApiResponse<List<Students>> response = ApiResponse.<List<Students>>builder()
                .code(200)
                .message("thành công")
                .data(students)
                .build();
        return ResponseEntity.ok(response);
    }


    // Tìm kiếm sinh viên theo id
    @GetMapping("/search/{id}")
    public ResponseEntity<ApiResponse<Students>> getSearchStudentById(@PathVariable int id) {
        for (Students st : students) {
            if (st.getId() == id) {
                ApiResponse<Students> response = ApiResponse.<Students>builder()
                        .code(200)
                        .message("thành công")
                        .data(st)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        //exception (lỗi)
    throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
    }

    // tìm theo tên hoặc sdt
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Students>>> getSearchStudent(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone) {

        List<Students> foundStudents = new ArrayList<>();

        if (phone != null) {
            Students student = students.stream()
                    .filter(st -> st.getNumberPhone().equals(phone))
                    .findFirst()
                    .orElseThrow(() -> new ApiException(ErrorCode.STUDENT_FIND_NOT));
            foundStudents.add(student);
        } else if (name != null) {
            foundStudents = students.stream()
                    .filter(st -> st.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        if (foundStudents.isEmpty()) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }

        ApiResponse<List<Students>> response = ApiResponse.<List<Students>>builder()
                .code(200)
                .message("Thành công")
                .data(foundStudents)
                .build();

        return ResponseEntity.ok(response);
    }




    // tim kiêếm theo ngày sinh
    @GetMapping("/search/birthday")
    public ResponseEntity<ApiResponse<List<Students>>> getStudentsByBirthdayRange(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<Students> foundStudents = students.stream()
                .filter(st -> !st.getBirthDate().isBefore(start) && !st.getBirthDate().isAfter(end))
                .collect(Collectors.toList());

        if (foundStudents.isEmpty()) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }

        ApiResponse<List<Students>> response = ApiResponse.<List<Students>>builder()
                .code(200)
                .message("Thành công")
                .data(foundStudents)
                .build();

        return ResponseEntity.ok(response);
    }



    // thêm sinh viên
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Students>> create(@RequestBody Students student) {
        student.setId((int)(Math.random() * 100000));
        students.add(student);
        ApiResponse<Students> response = ApiResponse.<Students>builder()
                .code(200)
                .message("đã thêm thành công")
                .data(student)
                .build();
        return ResponseEntity.ok(response);
    }

    // Xóa sinh viên theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Students>> deleteStudent(@PathVariable int id) {
        return students.stream().filter(e -> e.getId() == id).findFirst()
                .map(s -> {
                    students.remove(s);
                    ApiResponse<Students> successResponse = new ApiResponse<>(200, "đã xóa thành công", null);
                    return ResponseEntity.ok(successResponse);
                }).orElseGet(() -> {
                    throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
                });
    }


    // Cập nhật thông tin sinh viên theo id
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Students>> updateStudentById(@PathVariable int id, @RequestBody Students student) {
        return students.stream().filter(e -> e.getId() == id).findFirst()
                .map(e -> {
                    e.setName(student.getName());
                    e.setCode(student.getCode());
                    ApiResponse<Students> response = new ApiResponse<>(200, "đã cập nhật thành công", e);
                    return ResponseEntity.ok(response);
                }).orElseGet(() -> {
                    throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
                });
    }

}
