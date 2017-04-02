package com.example.android.startupindialivechat.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class ActivePendingChatItem {
    String problem;
    String number;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ActivePendingChatItem() {

    }
    public static String getJson(ArrayList<ActivePendingChatItem> activePendingChatItem){
        Gson gson = new Gson();
        return gson.toJson(activePendingChatItem);
    }

    public static ArrayList<ActivePendingChatItem> getActiveChatPendingItems(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<ActivePendingChatItem>>(){}.getType());
    }

    public ActivePendingChatItem(String problem, String number) {

        this.problem = problem;
        this.number = number;
    }
}
