package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicService {

    public Topic getTopicById(int id) {
        Topic topic = new Topic();


        try (PreparedStatement preparedStatement = ConnectionConfig.connection.prepareStatement(SQLTopic.GET_BY_ID.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                topic.setId(resultSet.getInt("id"));
                topic.setName(resultSet.getString("name"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topic;
    }

    public int getEventById(int id) {
        Event event = new Event();

        try (PreparedStatement preparedStatement = ConnectionConfig.connection.prepareStatement(SQLTopic.GET_EVENT_BY_ID.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               event.setId(resultSet.getInt("event_id"));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return event.getId();
    }

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

    public List<Topic> getAllTopicsByUser(User user) {
        List<Topic> topics = new ArrayList<>();


        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.GET_TOPICS_BY_USER.QUERY)) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Topic topic = this.getTopicById(resultSet.getInt("topic_id"));

                topics.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public List<Integer> getListAllTopicsByUser(User user) {
        List<Integer> list = new ArrayList<>();


        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.GET_TOPICS_BY_USER.QUERY)) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                list.add(resultSet.getInt("topic_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
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

    public void updateTopics(List<Topic> topics) {
        for (Topic topic : topics) {
            try (PreparedStatement preparedStatement = ConnectionConfig.connection
                    .prepareStatement(SQLTopic.UPDATE.QUERY)) {
                preparedStatement.setString(1, topic.getName());
                preparedStatement.setInt(2, topic.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
