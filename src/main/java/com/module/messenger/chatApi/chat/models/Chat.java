package com.module.messenger.chatApi.chat.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(value = "chat")
@Builder
public class Chat implements Serializable {

    private static final long serialVersionUID = 142466781L;

    @Id
    private String id;

    private String mid;

    private String message;

    private Date sendTime;


}
