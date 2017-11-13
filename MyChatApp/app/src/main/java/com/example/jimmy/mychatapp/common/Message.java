package com.example.jimmy.mychatapp.common;

import java.sql.Timestamp;

/**
 * Created by jimmy on 2017/11/12.
 */

public class Message {
    private Integer messageId;
    private String message;
    private Timestamp createdAt;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
