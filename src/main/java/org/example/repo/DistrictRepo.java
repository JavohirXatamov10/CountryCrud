package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.entity.District;
import org.example.entity.Region;
import org.example.payload.DistrictDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static District findById(Integer id) {
        String query = "select * from district where id=?";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new District(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("regionid"));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Integer id, District district) {
        String sql = "UPDATE district SET name = ?, regionid = ? WHERE id = ?";
        try(Connection connection= ConnectionManager.getDataSource().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, district.getName());
            preparedStatement.setInt(2,district.getRegionId() );
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(District district) {
        String sql = "UPDATE district SET name = ?, regionid = ?";
        try(Connection connection= ConnectionManager.getDataSource().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, district.getName());
            preparedStatement.setInt(2,district.getRegionId() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
