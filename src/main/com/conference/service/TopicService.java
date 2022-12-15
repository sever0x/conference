package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;
import com.conference.model.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicService {

    public List<Topic> getAllTopics(int id) {
        List<Topic> topics = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLEvent.GET_TOPIC_BY_EVENT_ID.QUERY)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Topic topic = new Topic(resultSet.getInt("id"),
                        resultSet.getString("name"));
                topics.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public void addTopics(List<Topic> topics, int eventId) {
        for (Topic topic : topics) {
            try (PreparedStatement preparedStatement = ConnectionConfig.connection
                    .prepareStatement(SQLTopic.INSERT.QUERY)) {
                preparedStatement.setString(1, topic.getName());
                preparedStatement.setInt(2, eventId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
