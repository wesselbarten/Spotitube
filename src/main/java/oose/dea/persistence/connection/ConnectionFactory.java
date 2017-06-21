package oose.dea.persistence.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * De connection factory maakt gebruik van het factory pattern
 */
public class ConnectionFactory {

    public Connection getConnection() throws SQLException {
        return new MySQLConnectionFactory().getConnection();
    }
}
