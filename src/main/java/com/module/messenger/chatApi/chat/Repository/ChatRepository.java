package com.module.messenger.chatApi.chat.Repository;

import com.module.messenger.chatApi.chat.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat, Object> {
    Flux<Chat> findAll();

}
