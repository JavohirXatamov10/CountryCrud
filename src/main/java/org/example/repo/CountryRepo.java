package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.entity.Country;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
