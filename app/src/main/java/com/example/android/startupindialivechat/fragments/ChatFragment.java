package com.example.android.startupindialivechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.startupindialivechat.R;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class ChatFragment extends android.support.v4.app.Fragment {
    public static ChatFragment newInstance(){
        Bundle args = new Bundle();
//        args.putString("strength", Strength.getJson(strength));
//        StrengthFragment strengthFragment = new StrengthFragment();
//        strengthFragment.setArguments(args);
        return  new ChatFragment();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_chat, container, false);


        return rootView;

    }

}
