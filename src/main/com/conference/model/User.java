package com.conference.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;

    private String login;

    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
