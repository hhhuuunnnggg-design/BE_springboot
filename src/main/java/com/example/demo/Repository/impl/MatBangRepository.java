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
        String query = """
                    SELECT
                        mb.id,
                        mb.ten,
                        mb.diachi,
                        mb.dientich,
                        lmb.tenMatBang AS loaiMatbang, -- Lấy tên loại mặt bằng
                        mb.giathue,
                        mb.ngaythue
                    FROM
                        matbang mb
                    JOIN
                        loaimatbang lmb ON mb.loaiMatBangId = lmb.loaiMatBangId
                """;

        try (PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                matbangs.add(MatBang.builder()
                        .id(resultSet.getInt("id")) // Lấy ID
                        .ten(resultSet.getString("ten")) // Lấy tên mặt bằng
                        .diachi(resultSet.getString("diachi")) // Lấy địa chỉ
                        .dientich(resultSet.getDouble("dientich")) // Lấy diện tích
                        .loaiMatbang(resultSet.getString("loaiMatbang")) // Lấy tên loại mặt bằng
                        .giathue(resultSet.getDouble("giathue")) // Lấy giá thuê
                        .ngaythue(resultSet.getDate("ngaythue").toLocalDate()) // Lấy ngày thuê
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching matbangs", e);
        }
        return matbangs;
    }

    @Override
    public List<MatBang> searchMatBang(int id, String ten, String diachi, Double dientich, String loaimatbang,
            Double giathue, String startngaythue) {
        List<MatBang> matbangs = new ArrayList<>();
        StringBuilder query = new StringBuilder("""
                    SELECT
                        mb.id,
                        mb.ten,
                        mb.diachi,
                        mb.dientich,
                        lmb.tenMatBang AS loaiMatbang,
                        mb.giathue,
                        mb.ngaythue
                    FROM
                        matbang mb
                    JOIN
                        loaimatbang lmb ON mb.loaiMatBangId = lmb.loaiMatBangId
                    WHERE 1=1
                """);

        if (id > 0) {
            query.append(" AND mb.id = ?");
        }
        if (ten != null && !ten.isEmpty()) {
            query.append(" AND mb.ten LIKE ?");
        }
        if (diachi != null && !diachi.isEmpty()) {
            query.append(" AND mb.diachi LIKE ?");
        }
        if (dientich != null) {
            query.append(" AND mb.dientich >= ?");
        }
        if (loaimatbang != null && !loaimatbang.isEmpty()) {
            query.append(" AND lmb.tenMatBang = ?");
        }
        if (giathue != null) {
            query.append(" AND mb.giathue >= ?");
        }
        if (startngaythue != null && !startngaythue.isEmpty()) {
            query.append(" AND mb.ngaythue >= ?");
        }

        System.out.println("Generated Query: " + query);

        try (PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query.toString())) {
            int index = 1;

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
            if (loaimatbang != null && !loaimatbang.isEmpty()) {
                preparedStatement.setString(index++, loaimatbang);
            }
            if (giathue != null) {
                preparedStatement.setDouble(index++, giathue);
            }
            if (startngaythue != null && !startngaythue.isEmpty()) {
                preparedStatement.setDate(index++, java.sql.Date.valueOf(startngaythue));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matbangs.add(MatBang.builder()
                            .id(resultSet.getInt("id"))
                            .ten(resultSet.getString("ten"))
                            .diachi(resultSet.getString("diachi"))
                            .dientich(resultSet.getDouble("dientich"))
                            .loaiMatbang(resultSet.getString("loaiMatbang"))
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
