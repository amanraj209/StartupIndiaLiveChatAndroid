package com.example.android.startupindialivechat.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.startupindialivechat.Adapters.AdminMessageAdapter;
import com.example.android.startupindialivechat.R;
import com.example.android.startupindialivechat.model.ActivePendingChatItem;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class ChatFragment extends android.support.v4.app.Fragment {
    private AdminMessageAdapter adapter;
    private ListView listView;
    public static final String TAG="chatfragment";
    public static ChatFragment newInstance(ArrayList<ActivePendingChatItem> list){
        Bundle args = new Bundle();
        args.putString("list",ActivePendingChatItem.getJson(list));
//        strengthFragment.setArguments(args);
        ChatFragment chatFragment=new ChatFragment();
        chatFragment.setArguments(args);
        return chatFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_chat, container, false);
        adapter=new AdminMessageAdapter(getActivity(),R.layout.list_item_chatlist,ActivePendingChatItem.getActiveChatPendingItems(getArguments().getString("list")));
        Log.d(TAG, "onCreateView: "+rootView);
        listView= (ListView) rootView.findViewById(R.id.frag_messageListView);
        listView.setAdapter(adapter);

        return rootView;

    }

}
