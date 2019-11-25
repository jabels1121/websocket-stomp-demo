package com.jaybe.websocketstompclientdemo.controllers.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPassObj {

    private String login;
    private String pass;

}
