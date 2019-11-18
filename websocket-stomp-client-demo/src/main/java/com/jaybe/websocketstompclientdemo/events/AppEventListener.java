package com.jaybe.websocketstompclientdemo.events;

import com.jaybe.websocketstompclientdemo.websocket.MyStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class AppEventListener {

    private final MyStompSessionHandler stompSessionHandler;

    public AppEventListener(MyStompSessionHandler stompSessionHandler) {
        this.stompSessionHandler = stompSessionHandler;
    }

    @EventListener
    public void contextRefreshed(ContextRefreshedEvent event) {
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());

        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);

        ListenableFuture<StompSession> connect = webSocketStompClient.connect("ws://localhost:8080/ws-demo", getWebSocketHttpBasicAuthHeader("foo", "foo") ,stompSessionHandler);


        log.info("bl");
    }

    private WebSocketHttpHeaders getWebSocketHttpBasicAuthHeader(final String login, final String pass) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, Collections.singletonList("Basic " + getBasicAuth(login, pass)));
        return headers;
    }

    private String getBasicAuth(String login, String pass) {
        var plainCreds = login + ":" + pass;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
}
