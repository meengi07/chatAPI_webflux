package com.module.messenger.chatApi.chat.Repository;

import com.module.messenger.chatApi.chat.models.Chat;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    @Tailable
    @Query("{sender:?0, receiver:?1}")
    Flux<Chat> findBySenders(String sender, String receiver);

    Flux<Chat> findAll();

}
