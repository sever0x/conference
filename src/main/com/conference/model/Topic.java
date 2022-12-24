package com.conference.model;

import com.conference.service.TopicService;
import com.conference.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Topic {

    private int id;

    private String name;



    public Topic(String name) {
        this.name = name;
    }

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Topic(){};


}
