package com.conference.dao;

import com.conference.config.ConnectionConfig;
import com.conference.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = ConnectionConfig.connection.createStatement();
            String SQL = "select * from user";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


}
