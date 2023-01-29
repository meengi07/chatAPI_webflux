package com.module.messenger.chatApi.chat.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(value = "chat")
public class Chat {

    @Id
    private String id;
    private String msg;
    private String sender;
    private String receiver;
    private Integer roomNum;
    private LocalDateTime createdAt;

}
