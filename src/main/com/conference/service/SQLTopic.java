package com.conference.service;

enum SQLTopic {
    SELECT_ALL("select * from event"),
    GET_BY_ID("select * from topic where id=?"),

    GET_EVENT_BY_ID("select event_id from topic where id=?"),
    GET_TOPICS_BY_USER("select * from topic_has_user where user_id=?"),

    GET_TOPICS_STATUS_BY_USER("select topic_status from topic_has_user where user_id=?"),
    INSERT("insert into topic (name, event_id) values ((?), (?))"),
    UPDATE("update topic set name=? where id=?");


    final String QUERY;

    SQLTopic(String QUERY) {
        this.QUERY = QUERY;
    }
}