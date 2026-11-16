package com.mtsarridho.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Database settings are loaded outside source code to avoid committed secrets. */
public final class KoneksiDatabase {
    private static Connection koneksi;

    private KoneksiDatabase() {}

    public static synchronized Connection getKoneksi() {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                return koneksi;
            }
            Properties config = loadConfiguration();
            String url = environmentOrConfig("SCHOOL_DB_URL", config, "db.url");
            String username = environmentOrConfig("SCHOOL_DB_USER", config, "db.username");
            String password = environmentOrConfig("SCHOOL_DB_PASSWORD", config, "db.password");
            if (url == null || url.trim().isEmpty() || username == null || username.trim().isEmpty()) {
                throw new IllegalStateException("Missing database settings. Copy config/database.properties.example "
                        + "to config/database.properties and set db.url and db.username.");
            }
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(url, username, password == null ? "" : password);
            return koneksi;
        } catch (SQLException e) {
            // Do not print the connection URL or credentials to logs.
            throw new IllegalStateException("Could not connect to the database. Check your local database configuration.", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC driver not found. Check NetBeans library dependencies.", e);
        }
    }

    private static Properties loadConfiguration() {
        Properties config = new Properties();
        String path = System.getProperty("school.config", "config/database.properties");
        File file = new File(path);
        if (file.isFile()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                config.load(stream);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load local database configuration file.", e);
            }
        }
        return config;
    }

    private static String environmentOrConfig(String envName, Properties config, String propertyName) {
        String value = System.getenv(envName);
        return value != null ? value : config.getProperty(propertyName);
    }
}
