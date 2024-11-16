package com.example.demo.crud;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor // tạo constructor có tham số
@NoArgsConstructor  // tạo constructor không tham số
//@RequiredArgsConstructor // tạo ra cho những thằng final
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Students {
     int id;
     String name;
     int code;
     String numberPhone;
    LocalDate birthDate;
}
