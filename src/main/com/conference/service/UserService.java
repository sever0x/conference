package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.dao.UserDAO;
import com.conference.model.Role;
import com.conference.model.Topic;
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
            statement.setString(2, user.getEmail());
            statement.setString(3, String.valueOf(user.getRole()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
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
        try (PreparedStatement statement1 = ConnectionConfig.connection.
                prepareStatement(SQLUser. DELETE_TOPIC_FROM_TOPIC_HAS_USER.QUERY);
             PreparedStatement  statement =ConnectionConfig.connection.
                prepareStatement(SQLUser.DELETE_USER.QUERY)) {
            statement1.setInt(1,user.getId());
            statement1.executeUpdate();
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setEmail(resultSet.getString("email"));
                user.setPermission(resultSet.getInt("permission"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void addUserPermission(User user) {
        try (PreparedStatement statement = ConnectionConfig.connection.
                prepareStatement(SQLUser.UPDATE_PERMISSION.QUERY)) {
            statement.setInt(1, 1);
            statement.setString(2, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeUserPermission(User user) {
        try (PreparedStatement statement = ConnectionConfig.connection.
                prepareStatement(SQLUser.CHANGE_USER_PERMISSION.QUERY)) {
            statement.setInt(1, 0);
            statement.setString(2, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserTopic(User user, Topic topic) {
        try (PreparedStatement statement = ConnectionConfig.connection.prepareStatement(SQLUser.INSERT_USER_TOPIC_LIST.QUERY)) {
            statement.setInt(1, topic.getId());
            statement.setInt(2, user.getId());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean getTopicById(Topic topic) {

        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLUser.GET_USER_BY_TOPIC_ID.QUERY)) {
            preparedStatement.setInt(1, topic.getId());

            ResultSet resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}

enum SQLUser {
    SELECT_ALL("select * from user"),
    GET_BY_ID("select * from user where id=?"),
    GET_BY_LOGIN("select * from user where login=?"),
    UPDATE("update user set login=?, email=?, role=? where id=?"),
    INSERT("insert into user (login, password, email) values ((?), (?), (?))"),
    UPDATE_PERMISSION("update user set permission=? where login=?"),
    CHANGE_USER_PERMISSION("update user set permission=? where login=?"),
    INSERT_USER_TOPIC_LIST("insert into topic_has_user (topic_id, user_id) values ((?), (?))"),
    GET_USER_BY_TOPIC_ID("select * from topic_has_user where topic_id=?"),
    DELETE_USER("delete from user where id=?"),
    DELETE_TOPIC_FROM_TOPIC_HAS_USER("delete from topic_has_user where user_id=?");


    final String QUERY;

    SQLUser(String QUERY) {
        this.QUERY = QUERY;
    }
}