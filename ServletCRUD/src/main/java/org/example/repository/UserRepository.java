package org.example.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Connection {
    public List<String> getUserRoles(String email) throws SQLException, ClassNotFoundException {
        setConnection();
        List<String> roles = new ArrayList<>();
        try {
            String sql = "SELECT * from roles r inner join user_roles u on r.id = u.role_id inner join user "+
                    "on user.id = u.user_id WHERE user.email = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, email);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String role = resultSet.getString("role");
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return roles;
    }
}
