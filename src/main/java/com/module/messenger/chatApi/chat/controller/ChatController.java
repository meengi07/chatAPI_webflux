package com.module.messenger.chatApi.chat.controller;

import com.module.messenger.chatApi.chat.Repository.ChatRepository;
import com.module.messenger.chatApi.chat.models.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatRepository chatRepository;


    @GetMapping(value="/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessage(@PathVariable String sender, @PathVariable String receiver) {
        return chatRepository.findBySenders(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }


    @PostMapping("/chat")
    public Mono<Chat> setMessage(@RequestBody Chat chat) {
        chat.setSendTime(LocalDateTime.now());
        return chatRepository.save(chat);
    }
}
