package com.jaybe.websocketstompclientdemo.events;

import com.jaybe.websocketstompclientdemo.websocket.MyStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

import static com.jaybe.websocketstompclientdemo.utils.WebHelperFunctions.getWebSocketHttpBasicAuthHeader;

@Component
@Slf4j
public class AppEventListener {

    private final MyStompSessionHandler stompSessionHandler;

    public AppEventListener(MyStompSessionHandler stompSessionHandler) {
        this.stompSessionHandler = stompSessionHandler;
    }

    //@EventListener
    public void contextRefreshed(ContextRefreshedEvent event) {
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());

        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);

        ListenableFuture<StompSession> connect = webSocketStompClient.connect("ws://localhost:8080/ws-demo", getWebSocketHttpBasicAuthHeader("foo", "foo") ,stompSessionHandler);

        log.info("bl");
    }
}
