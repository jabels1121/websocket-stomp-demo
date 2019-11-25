package com.jaybe.websocketstompclientdemo.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.util.Collections;

public class WebHelperFunctions {

    public static WebSocketHttpHeaders getWebSocketHttpBasicAuthHeader(final String login, final String pass) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, Collections.singletonList("Basic " + getBasicAuth(login, pass)));
        return headers;
    }

    public static String getBasicAuth(String login, String pass) {
        var plainCreds = login + ":" + pass;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }

}
