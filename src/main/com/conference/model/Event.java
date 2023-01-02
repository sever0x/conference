package com.conference.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Event {
    private int id;

    private String name;

    private String describe;

    private String date;

    private String place = "Kiev";

    private List<Topic> topics;

    @Override
    public String toString() {
        return "Название : " + name +
                "\nОписание : " + describe +
                "\nДата : " + date +
                "\nМесто проведения  : " + place;
    }
}