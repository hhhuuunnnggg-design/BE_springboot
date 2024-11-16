package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
// xuất thông báo error
public enum ErrorCode {
    STUDENT_FIND_NOT(404,"Không tìm thấy ID sinh viên",HttpStatus.NOT_FOUND),
    STUDENT_FIND_NOT_NAME(404,"Không tìm thấy Tên sinh viên",HttpStatus.NOT_FOUND),
    STUDENT_FIND_NOT_PHONE(404,"Không tìm thấy SĐT sinh viên",HttpStatus.NOT_FOUND)

    ;

    Integer code;
    String message;
    HttpStatus httpStatus;
}
