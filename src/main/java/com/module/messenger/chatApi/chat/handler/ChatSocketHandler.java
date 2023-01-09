//package com.module.messenger.chatApi.chat.handler;
//
//import com.module.messenger.chatApi.chat.Repository.ChatRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class ChatSocketHandler implements WebSocketHandler {
//    private final ChatRepository chatRepository;
//
//    @Override
//    public List<String> getSubProtocols() {
//        return WebSocketHandler.super.getSubProtocols();
//    }
//
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//        String protocol = session.getHandshakeInfo().getSubProtocol();
//        WebSocketMessage message = session.textMessage(protocol);
//        return doSend(session, Mono.just(message));
//    }
//
//    private Mono<Void> doSend(WebSocketSession session, Publisher<WebSocketMessage> out) {
//        return session.send(Mono.delay(Duration.ofMillis(100)).thenMany(out));
//    }
//
//    private Mono<Void> doSend(WebSocketSession session, Publisher<WebSocketMessage>)
//}
