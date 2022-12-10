package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.dao.UserDAO;
import com.conference.model.Role;
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
            statement.setString(3, user.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = ConnectionConfig.connection.prepareStatement(SQLUser.UPDATE.QUERY)) {
            statement.setString(1, user.getLogin());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        User user = new User();

        try (PreparedStatement statement = ConnectionConfig.connection
                .prepareStatement(SQLUser.GET_BY_ID.QUERY)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();

        try (PreparedStatement statement = ConnectionConfig.connection
                .prepareStatement(SQLUser.GET_BY_LOGIN.QUERY)) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = ConnectionConfig.connection.createStatement();
             
             ResultSet resultSet = statement.executeQuery(SQLUser.SELECT_ALL.QUERY)) {
            ResultSet resultSet1 = statement.executeQuery(SQLUser.SELECT_ALL.QUERY);
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
    GET_BY_ID("select * from user where id=?"),
    GET_BY_LOGIN("select * from user where login=?"),
    UPDATE("update user set login=?, email=? where id=?"),
    INSERT("insert into user (login, password, email) values ((?), (?), (?))");

    final String QUERY;

    SQLUser(String QUERY) {
        this.QUERY = QUERY;
    }
}