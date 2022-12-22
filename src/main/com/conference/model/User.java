package com.conference.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private int id;

    private String login;

    private String password;

    private String email;

    private Role role ;

    private int permission ;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }


}
