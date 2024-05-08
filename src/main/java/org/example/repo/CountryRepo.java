package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.entity.Country;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryRepo {
    public static List<Country> findAll() {
        String query ="select * from country";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries  = new ArrayList<>();
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            return countries;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(Integer id) {
        String query = "delete from country where id=?";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void addCountry(Country country) {
        String query = "insert into country(name) values (?)";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Integer id, Country country) {
        String sql = "UPDATE country SET name = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getDataSource().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,country.getName());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Country findById(Integer id) {
        String query = "select * from country where id=?";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
