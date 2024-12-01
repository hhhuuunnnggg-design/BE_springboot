package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.Model.enums.Gender;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor // tạo constructor có tham số
@NoArgsConstructor // tạo constructor không tham số
// @RequiredArgsConstructor // tạo ra cho những thằng final
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // int id;
    Integer id;
    String name;
    int code;

    // @Column(name = "number_phone")
    String numberPhone;

    LocalDate birthDate;

    @Enumerated(EnumType.STRING) // Đảm bảo enum được lưu dưới dạng chuỗi trong DB
    Gender gender;
    String avatar;
    BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "test_id")
    Tests test;
}
