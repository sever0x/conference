package com.conference.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Event {
    private int id;

    private String name;

    private String describe;

    private Date date = new Date();

    private String place = "Somewhere";

    @Override
    public String toString() {
        return "Название : " + name +
                "\nОписание : " + describe +
                "\nДата : " + date +
                "\nМесто проведения  : " + place;
    }
}