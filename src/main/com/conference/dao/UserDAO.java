package com.conference.dao;

import com.conference.model.User;

import java.util.List;

public interface UserDAO {

    void saveNewUser(User user);

    void updateUser(User user);

    User getUserById(int id);

    User getUserByLogin(String login);

    void deleteUser(User user);

    List<User> getAllUsers();
}
