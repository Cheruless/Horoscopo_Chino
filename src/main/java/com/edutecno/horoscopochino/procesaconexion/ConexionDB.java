package com.edutecno.horoscopochino.procesaconexion;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDB {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/bdd_mod5_exequiel");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        ds = new HikariDataSource(config);
    }

    public static Connection generaPoolConexion() throws SQLException {
        return ds.getConnection();
    }

    public static void cerrarPool() {
        if (ds != null) {
            ds.close();
        }
    }
}
