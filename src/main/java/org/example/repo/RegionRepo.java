package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.payload.RegionDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class RegionRepo {
    public static List<RegionDTO> findAllDTORegions() {
        String query ="select r.id,c.name as countryName ,r.name from region r join public.country c on c.id = r.countryid";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RegionDTO>  regionsDto  = new ArrayList<>();
            while (resultSet.next()) {
                regionsDto.add(new RegionDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryName")
                ));
            }
            return  regionsDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(Integer id) {
        String query = "delete from region where id=?";
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
