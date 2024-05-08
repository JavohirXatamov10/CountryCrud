package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.payload.DistrictDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DistrictRepo {

    public static List<DistrictDTO> findAll() {
        String query="select d.id, r.name as regionName, d.name from  district d join public.region r on r.id = d.regionid";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DistrictDTO>  districtDTOS  = new ArrayList<>();
            while (resultSet.next()) {
                districtDTOS.add(new DistrictDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("regionName")
                ));
            }
            return  districtDTOS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(Integer id) {
        String query = "delete from district where id=?";
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
