package com.conference.model;

import com.conference.service.TopicService;
import com.conference.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private int id;

    private String login;

    private String password;

    private String email;

    private Role role;

    private int permission;

    private List<Topic> topics = new ArrayList<>();

    private Topic topic;
    private int topicStatus;


    public List<Topic> addTopic(Topic topic) {
        topics.add(topic);
        return topics;

    }

    public boolean userHasTopic(int id) {
        boolean result = false;
        TopicService topicService = new TopicService();
        List<Integer> list = new ArrayList<>();
        list = topicService.getListAllTopicsByUser(this);
        int[] idTopic = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int topic1 = list.get(i);
            idTopic[i] = topic1;

        }


        for (int i = 0; i < idTopic.length; i++) {
            if (idTopic[i] == id) {
                return true;
            }

        }
        return false;
    }

    public void setTopicStatus(int status) {
        topicStatus = status;
    }

    public int getTopicStatus(int id) {
        TopicService topicService = new TopicService();
        List<Topic> topics = topicService.getAllTopicsByUser(this);
        int status = 0;
        for (int i = 0; i < topics.size(); i++) {
            Topic topic1 = topics.get(i);
            if (topic1.getId() == id) {
                status = topic1.getTopicStatus();
            }
        }
        return status;
    }
}