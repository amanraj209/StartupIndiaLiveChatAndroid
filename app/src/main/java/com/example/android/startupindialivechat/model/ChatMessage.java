package com.example.android.startupindialivechat.model;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class ChatMessage {
    String text;

    public ChatMessage(String text) {
        this.text = text;
    }

    public ChatMessage() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
