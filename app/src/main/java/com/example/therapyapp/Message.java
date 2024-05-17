package com.example.therapyapp;


import java.util.HashMap;
import java.util.Map;

public class Message {
    private String senderId;
    private String messageText;
    private String receiverId;

    // Boş yapıcı metot, Firestore için gereklidir.
    public Message() {
    }

    public Message(String senderId, String messageText, String receiverId) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("senderId", senderId);
        result.put("messageText", messageText);
        result.put("receiverId", receiverId);
        return result;
    }
}