package com.conference.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Topic {

    private int id;

    private String name;

    private String speakerLogin;

    public Topic(int id, String name, String speakerLogin) {
        this.id = id;
        this.name = name;
        this.speakerLogin = speakerLogin;
    }

    public Topic(String name) {
        this.name = name;
    }

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Topic(int id) {
        this.id = id;
    }

    public Topic() {
    }


}
