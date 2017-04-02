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
import com.example.android.startupindialivechat.model.ActivePendingChatItem;
import com.example.android.startupindialivechat.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class AdminActivityAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;
    ArrayList<ActivePendingChatItem> pending, active;

    public AdminActivityAdapter(FragmentManager fm, ArrayList<ActivePendingChatItem> pending, ArrayList<ActivePendingChatItem> active) {
        super(fm);
        this.pending = pending;
        this.active = active;
        fragments=new Fragment[2];
    }

    @Override

    public Fragment getItem(int position) {

        if (fragments[position] == null) {
            if (position == 0) {
                fragments[position] = ChatFragment.newInstance(active);
            } else {
                fragments[position] = ChatFragment.newInstance(pending);

            }
        }
        return fragments[position];

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