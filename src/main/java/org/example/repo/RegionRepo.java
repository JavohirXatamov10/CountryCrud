package org.example.repo;

import org.example.config.ConnectionManager;
import org.example.entity.Country;
import org.example.entity.Region;
import org.example.payload.RegionDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static List<Region> findAll() {
        String query ="select * from region";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Region>  regions  = new ArrayList<>();
            while (resultSet.next()) {
                regions.add(new Region(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("countryId")
                ));
            }
            return  regions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveRegion(Region region) {
        String sql="insert into region (name, countryId) values (?,?)";
        try (
            Connection connection = ConnectionManager.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getCountryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Region findById(Integer id) {
        String query = "select * from region where id=?";
        try (
                Connection connection = ConnectionManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (new Region(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("countryId")));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void update(Integer id, Region region) {
        String sql = "UPDATE region SET name = ?, countryid = ? WHERE id = ?";
        try(Connection connection= ConnectionManager.getDataSource().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql))
        {
                preparedStatement.setString(1, region.getName());
                preparedStatement.setInt(2,region.getCountryId());
                preparedStatement.setInt(3,id);
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
