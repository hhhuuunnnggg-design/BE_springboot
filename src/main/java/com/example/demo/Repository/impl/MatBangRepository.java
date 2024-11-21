package com.example.demo.Repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.MatBang;
import com.example.demo.Repository.BaseRepository;
import com.example.demo.Repository.IMatBangRepository;

@Repository
public class MatBangRepository implements IMatBangRepository {

    @Override
    public List<MatBang> findAll() {
        List<MatBang> matbangs = new ArrayList<>();
        String query = "SELECT * FROM matbang"; // Sửa chính tả SQL

        try (PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                matbangs.add(MatBang.builder()
                        .id(resultSet.getInt("id"))
                        .ten(resultSet.getString("ten"))
                        .diachi(resultSet.getString("diachi"))
                        .dientich(resultSet.getDouble("dientich"))
                        .loaiMatbang(resultSet.getInt("loaiMatbang"))
                        .giathue(resultSet.getDouble("giathue"))
                        .ngaythue(resultSet.getDate("ngaythue").toLocalDate()) // Sửa chuyển đổi ngày tháng
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching employees", e);
        }
        return matbangs;
    }

    @Override
    public List<MatBang> searchMatBang(int id, String ten, String diachi, Double dientich, Integer loaimatbang,
            Double giathue, String startngaythue) {
        List<MatBang> matbangs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM matbang WHERE 1=1");

        if (id > 0) {
            query.append(" AND id = ?");
        }
        if (ten != null && !ten.isEmpty()) {
            query.append(" AND ten LIKE ?");
        }
        if (diachi != null && !diachi.isEmpty()) {
            query.append(" AND diachi LIKE ?");
        }
        if (dientich != null) {
            query.append(" AND dientich >= ?");
        }
        // if (loaimatbang != null && !loaimatbang.isEmpty()) {
        // query.append(" AND loaiMatbang = ?");
        // }
        if (giathue != null) {
            query.append(" AND giathue >= ?");
        }
        if (startngaythue != null && !startngaythue.isEmpty()) {
            query.append(" AND ngaythue >= ?");
        }
        System.out.println(query);

        try (PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query.toString())) {
            int index = 1;

            // Gán giá trị tham số
            if (id > 0) {
                preparedStatement.setInt(index++, id);
            }
            if (ten != null && !ten.isEmpty()) {
                preparedStatement.setString(index++, "%" + ten + "%");
            }
            if (diachi != null && !diachi.isEmpty()) {
                preparedStatement.setString(index++, "%" + diachi + "%");
            }
            if (dientich != null) {
                preparedStatement.setDouble(index++, dientich);
            }
            // if (loaimatbang != null && !loaimatbang.isEmpty()) {
            // preparedStatement.setString(index++, loaimatbang);
            // }
            if (giathue != null) {
                preparedStatement.setDouble(index++, giathue);
            }
            if (startngaythue != null && !startngaythue.isEmpty()) {
                preparedStatement.setDate(index++, java.sql.Date.valueOf(startngaythue));
            }

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matbangs.add(MatBang.builder()
                            .id(resultSet.getInt("id"))
                            .ten(resultSet.getString("ten"))
                            .diachi(resultSet.getString("diachi"))
                            .dientich(resultSet.getDouble("dientich"))
                            .loaiMatbang(resultSet.getInt("loaiMatbang"))
                            .giathue(resultSet.getDouble("giathue"))
                            .ngaythue(resultSet.getDate("ngaythue").toLocalDate())
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while searching matbangs", e);
        }

        return matbangs;
    }

    @Override
    public MatBang findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
