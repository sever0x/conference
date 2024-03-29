package com.conference.service;

import com.conference.config.ConnectionConfig;
import com.conference.model.Topic;
import com.conference.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicService {

    private UserService userService = new UserService();

    public Topic getTopicById(int id) {
        Topic topic = new Topic();

        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.GET_BY_ID.QUERY)) {
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

    public int getEventIdByTopicId(int id) {
        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.GET_EVENT_ID_BY_TOPIC_ID.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("event_id");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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

    public List<Topic> getAllTopicsWithSpeakers(int id) {
        List<Topic> topics = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.SELECT_ALL_TOPICS_BY_EVENT_WITH_SPEAKERS.QUERY)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Topic topic = new Topic(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"));
                topics.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public void makeRequestAsTopicSpeaker(int userId, int topicId) {
        try (PreparedStatement statement = ConnectionConfig.connection
                .prepareStatement(SQLTopic.CREATE_REQUEST.QUERY)) {

            statement.setInt(1, userId);
            statement.setInt(2, topicId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

enum SQLTopic {
    SELECT_ALL("select * from event"),
    /**
     * Соединяет две таблицы topic и user
     */
    SELECT_ALL_TOPICS_BY_EVENT_WITH_SPEAKERS("select topic.id, topic.name, user.login from topic " +
            "left join topic_has_user on topic_has_user.topic_id = topic.id " +
            "left join user on user.id = topic_has_user.user_id where event_id=?"),
    GET_BY_ID("select * from topic where id=?"),

    GET_EVENT_ID_BY_TOPIC_ID("select event_id from topic where id=?"),
    GET_TOPICS_BY_USER("select * from topic_has_user where user_id=?"),

    GET_TOPICS_STATUS_BY_USER("select topic_status from topic_has_user where user_id=?"),
    INSERT("insert into topic (name, event_id) values ((?), (?))"),
    UPDATE("update topic set name=? where id=?"),

    CREATE_REQUEST("insert into speaker_request (user_id, topic_id) values ((?), (?))");

    final String QUERY;

    SQLTopic(String QUERY) {
        this.QUERY = QUERY;
    }
}