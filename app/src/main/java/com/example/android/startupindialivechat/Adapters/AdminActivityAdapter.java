package com.example.android.startupindialivechat.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.startupindialivechat.R;
import com.example.android.startupindialivechat.fragments.ChatFragment;
import com.example.android.startupindialivechat.model.ChatMessage;

import java.util.List;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class AdminActivityAdapter extends FragmentStatePagerAdapter {
    public AdminActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override

    public Fragment getItem(int position) {
        if (position == 0) {
            return ChatFragment.newInstance();
        } else {
            ChatFragment w = ChatFragment.newInstance();
            return w;
        }

    }

    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Active";
        } else {
            return "Pending";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}