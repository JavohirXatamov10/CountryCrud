package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import javax.sql.DataSource;

public class ConnectionManager {
    @Getter
    public static DataSource dataSource;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/crudcountrymvc");
        config.setUsername("postgres");
        config.setPassword("root123");
        config.setMinimumIdle(10);
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30_000);
        dataSource = new HikariDataSource(config);
    }

}
