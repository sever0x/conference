package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private TopicService topicService = new TopicService();

    public void addEvent(Event event) {
        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLEvent.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getDescribe());
            preparedStatement.setTimestamp(3, Timestamp.valueOf("2022-12-12 00:00:00"));
            preparedStatement.setString(4, event.getPlace());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                topicService.addTopics(event.getTopics(), resultSet.getInt(1));
            }
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
                event.setTopics(topicService.getAllTopics(event.getId()));
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
                event.setId(resultSet.getInt("id"));
                event.setPlace(resultSet.getString("place"));
                event.setName(resultSet.getString("name"));
                event.setDescribe(resultSet.getString("descr"));
                event.setDate(resultSet.getTimestamp("date"));
                event.setTopics(topicService.getAllTopics(resultSet.getInt("id")));
                listEvent.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listEvent;
    }

    public void updateEvent(Event event) {
        try (PreparedStatement statement = ConnectionConfig.connection.prepareStatement(SQLEvent.UPDATE.QUERY)) {
            statement.setString(1, event.getName());
            statement.setString(2, event.getDescribe());
//            statement.setString(3, String.valueOf(event.getDate()));
            statement.setString(3, event.getPlace());
            statement.setInt(4, event.getId());
            topicService.updateTopics(event.getTopics());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}