package com.example.jimmy.mychatapp.common;

/**
 * Created by jimmy on 2017/11/12.
 */

public class UserMessage extends BaseMessage {
    private Integer messageId;
    private String message,userName;
    private long createdAt;

    @Override
    public Integer getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
