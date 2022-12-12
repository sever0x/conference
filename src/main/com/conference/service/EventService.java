package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    public void addEvent(Event event) {
        try (PreparedStatement preparedStatement = ConnectionConfig.connection.prepareStatement((SQLEvent.INSERT.QUERY))) {
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getDescribe());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Event getEvent(int id) {
        Event event = new Event();
        try (PreparedStatement preparedStatement = ConnectionConfig.connection.prepareStatement(SQLEvent.GET_BY_ID.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescribe(resultSet.getString("descr"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return event;
    }

    public List<Event> getAllEvent() {
        List<Event> listEvent = new ArrayList<>();
        try (Statement statement = ConnectionConfig.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLEvent.SELECT_ALL.QUERY)) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setName(resultSet.getString("name"));
                event.setDescribe(resultSet.getString("descr"));
                event.setDate(resultSet.getDate("date"));
                listEvent.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listEvent;
    }
}