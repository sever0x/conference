package com.conference.model;

import com.conference.service.TopicService;
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


    public List<Topic> addTopic(Topic topic) {
        topics.add(topic);
        return topics;

    }

    public boolean userHasTopic(int id) {
        boolean result = false;
        TopicService topicService = new TopicService();
        List<Integer> list;
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
}