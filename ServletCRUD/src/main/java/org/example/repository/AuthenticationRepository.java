package org.example.repository;

import org.example.model.User;
import org.example.utils.HTTPUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthenticationRepository extends Connection{
    private String sql = "";

    public void register(User user) throws SQLException, ClassNotFoundException {
        setConnection();
        try {
            sql = "insert into user(userName,email,password) values(?,?,?);";

            PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, user.getUserName());
            prepareStatement.setString(2, user.getEmail());

            String passwordHash = HTTPUtils.encodePassword(user.getPassword());
            prepareStatement.setString(3, passwordHash);
            prepareStatement.executeUpdate();

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public User login(User userDTO) throws SQLException, ClassNotFoundException {
        setConnection();
        User user = null;
        try {
            sql = "select * from user where email=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, userDTO.getEmail());
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(id, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return user;
    }
}
