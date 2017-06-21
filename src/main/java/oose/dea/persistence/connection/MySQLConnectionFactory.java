package oose.dea.persistence.connection;


import com.mysql.cj.jdbc.MysqlDataSource;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MySQLConnectionFactory {

    /**
     * Requirement D1 wordt aangetoond, bij het wisselen van database hoeft de code niet opnieuw gecompileerd te worden,
     * omdat de database configuratie is opgeslagen in een database.properties bestand
     */
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try(
                InputStream inputStream = classloader.getResourceAsStream("database.properties")
        ) {
            properties.load(inputStream);
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty("databaseurl"));
            mysqlDataSource.setUser(properties.getProperty("user"));
            mysqlDataSource.setPassword(properties.getProperty("password"));

            return mysqlDataSource.getConnection();
        } catch (IOException e) {
            throw new RuntimeException("There is a problem with connecting to the database.", e);
        }
    }
}
