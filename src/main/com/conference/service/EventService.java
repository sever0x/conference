package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private TopicService topicService = new TopicService();

    public void addEvent(Event event) {
        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLEvent.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getDescribe());
            preparedStatement.setString(3, event.getDate());
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
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(resultSet.getTimestamp("date"));
                event.setDate(date);
                event.setPlace(resultSet.getString("place"));
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

                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(resultSet.getTimestamp("date"));
                event.setDate(date);

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
            statement.setString(3, event.getDate());
            statement.setString(4, event.getPlace());
            statement.setInt(5, event.getId());
            topicService.updateTopics(event.getTopics());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

enum SQLEvent{
    SELECT_ALL("select * from event"),
    GET_BY_ID("select * from event where id=?"),
    GET_TOPIC_BY_EVENT_ID("select id, name from topic where event_id=?"),
    INSERT("insert into event (name, descr, date, place) values ((?), (?),(?), (?))"),
    UPDATE("update event set name=?, descr=?, date=?, place=? where id=?");

    final String QUERY;

    SQLEvent(String QUERY) {
        this.QUERY = QUERY;
    }
}