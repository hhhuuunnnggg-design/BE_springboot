package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.Model.enums.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor // tạo constructor có tham số
@NoArgsConstructor // tạo constructor không tham số
// @RequiredArgsConstructor // tạo ra cho những thằng final
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    int id;
    String name;
    int code;
    String numberPhone;
    LocalDate birthDate;
    Gender gender;

    BigDecimal salary;// tiền
    // String salaryRange;// khoảng tiền

}
