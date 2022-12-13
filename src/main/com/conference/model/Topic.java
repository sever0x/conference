package com.conference.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic {

    private int id;

    private String name;

    public Topic(String name) {
        this.name = name;
    }
}
