package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.dao.UserDAO;
import com.conference.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDAO {

    @Override
    public void saveNewUser(User user) {
        try (PreparedStatement statement = ConnectionConfig.connection.prepareStatement(SQLUser.INSERT.QUERY)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = ConnectionConfig.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLUser.SELECT_ALL.QUERY)) {

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}

enum SQLUser {
    SELECT_ALL("select * from user"),
    GET("select * from user where id = (?)"),
    SET(""),
    INSERT("insert into user (login, password) values ((?), (?))");

    final String QUERY;

    SQLUser(String QUERY) {
        this.QUERY = QUERY;
    }
}