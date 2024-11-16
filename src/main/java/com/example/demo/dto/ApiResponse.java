package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor // tạo constructor có tham số
@NoArgsConstructor  // tạo constructor không tham số
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //dữ liệu trả về mà nó thông bào null thì bỏ qua lun
public class ApiResponse<T> {
    Integer code;
    String message;
    T data;
}
