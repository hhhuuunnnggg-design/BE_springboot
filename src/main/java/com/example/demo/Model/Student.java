package com.example.demo.Model;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor // tạo constructor có tham số
@NoArgsConstructor // tạo constructor không tham số
// @RequiredArgsConstructor // tạo ra cho những thằng final
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    int id;
    String name;
    int code;
    String numberPhone;
    LocalDate birthDate;
    String gender;

}
