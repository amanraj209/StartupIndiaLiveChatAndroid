package com.example.android.startupindialivechat.model;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class ChatMessage {
    String text;
    boolean isMine;

    public ChatMessage() {
    }

    public ChatMessage(String text, boolean isMine) {

        this.text = text;
        this.isMine = isMine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
