package com.module.messenger.chatApi.chat.controller;

import com.module.messenger.chatApi.chat.models.Chat;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private final List<RSocketRequester> CLIENTS = new ArrayList<>();

    @ConnectMapping
    public void onConnect(RSocketRequester socket) {
        socket
                .rsocket()
                .onClose()
                .doFirst(() -> {
                    CLIENTS.add(socket);
                })
                .doOnError(e -> e.printStackTrace())
                .doFinally(consum -> CLIENTS.remove(socket))
                .subscribe();
    }

    @MessageMapping("message")
    public Mono<Chat> message(Chat chat) {
        this.sendMessage(chat);
        return Mono.just(chat);
    }

    @MessageMapping("send")
    public void sendMessage(Chat chat) {

        Chat chatting = Chat.builder()
                .id(chat.getId())
                .mid(chat.getMid())
                .message(chat.getMessage())
                .sendTime(chat.getSendTime()).build();

        Flux.fromIterable(CLIENTS)
                .doOnNext(n -> {
                    n.route("")
                            .data(chatting)
                            .send()
                            .subscribe();
                }).subscribe();


    }
}
