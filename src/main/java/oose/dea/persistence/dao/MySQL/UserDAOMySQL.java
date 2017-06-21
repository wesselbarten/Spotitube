package oose.dea.persistence.dao.MySQL;

import oose.dea.domain.User;
import oose.dea.persistence.connection.ConnectionFactory;
import oose.dea.persistence.dao.IUserDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementatie voor de MySQL userDAO
 * Requirement IM1 en D2 worden aangetoond, door het implementeren van de userDAO interface kan er makkelijk
 * gewisseld worden van relationele database
 */
public class UserDAOMySQL implements IUserDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public User findByEmailAndPassword(String email, String password) {
        String sqlQuery = "SELECT * FROM Users WHERE email=? AND password=?";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("user_id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("User could not be validated due to persistence problem.", e);
        }
        return null;
    }
}
