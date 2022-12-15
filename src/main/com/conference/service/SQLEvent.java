package com.conference.service;

enum SQLEvent{
    SELECT_ALL("select * from event"),
    GET_BY_ID("select * from event where id=?"),
    //        GET_BY_LOGIN("select * from user where login=?"),
//        UPDATE("update user set login=?, email=? where id=?"),
    GET_TOPIC_BY_EVENT_ID("select name from topic where event_id=?"),
    INSERT("insert into event (name, descr, date, place) values ((?), (?),(?), (?))"),
    UPDATE("update event set name=?, descr=?, place=? where id=?");

    final String QUERY;

    SQLEvent(String QUERY) {
        this.QUERY = QUERY;
    }
}