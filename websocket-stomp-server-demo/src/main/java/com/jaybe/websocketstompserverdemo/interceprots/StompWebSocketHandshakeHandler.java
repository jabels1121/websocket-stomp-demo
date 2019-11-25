package com.jaybe.websocketstompserverdemo.interceprots;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.Map;

@Component
public class StompWebSocketHandshakeHandler implements HandshakeHandler {



    @Override
    public boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws HandshakeFailureException {
        return false;
    }
}
