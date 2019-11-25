package com.jaybe.websocketstompclientdemo.controllers;

import com.jaybe.websocketstompclientdemo.controllers.params.LoginPassObj;
import com.jaybe.websocketstompclientdemo.websocket.MyStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import static com.jaybe.websocketstompclientdemo.utils.WebHelperFunctions.getBasicAuth;
import static com.jaybe.websocketstompclientdemo.utils.WebHelperFunctions.getWebSocketHttpBasicAuthHeader;

@RestController
@Slf4j
public class WebSocketConnectionController {

    private static final String WEBSOCKET_SERVER_HANDSHAKE_URL = "ws://localhost:8080/ws-demo";
    public static final String HTTP_LOCALHOST_8080_HELLO = "http://localhost:8080/rest/api/hello";

    private final MyStompSessionHandler stompSessionHandler;
    private final WebSocketStompClient webSocketStompClient;

    public WebSocketConnectionController(MyStompSessionHandler stompSessionHandler,
                                         WebSocketStompClient webSocketStompClient) {
        this.stompSessionHandler = stompSessionHandler;
        this.webSocketStompClient = webSocketStompClient;
    }

    @PostMapping(path = "/websocket/connect")
    public void createStompWebsocketSession(@RequestBody LoginPassObj loginPassObj) {
        webSocketStompClient.connect(WEBSOCKET_SERVER_HANDSHAKE_URL, getWebSocketHttpBasicAuthHeader(loginPassObj.getLogin(), loginPassObj.getPass()), stompSessionHandler);
    }

    @PostMapping(path = "/server/auth")
    public String getServerAuth(@RequestBody LoginPassObj loginPassObj) {
        var restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + getBasicAuth(loginPassObj.getLogin(), loginPassObj.getPass()));
        ResponseEntity<String> exchange = restTemplate.exchange(HTTP_LOCALHOST_8080_HELLO, HttpMethod.GET, new HttpEntity(headers), String.class);
        return exchange.getBody();
    }

}
