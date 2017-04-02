package com.example.android.startupindialivechat;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.startupindialivechat.Adapters.AdminActivityAdapter;
import com.example.android.startupindialivechat.model.ActivePendingChatItem;

import java.util.ArrayList;

public class AdminPanelActivity extends AppCompatActivity {
    ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ArrayList<ActivePendingChatItem> pending,active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        pending=new ArrayList<ActivePendingChatItem>();
        for(int i=0;i<10;++i){
            ActivePendingChatItem a=new ActivePendingChatItem(i+1+"",i+1+"");
            pending.add(a);

        }
        active=new ArrayList<ActivePendingChatItem>();
        for(int i=0;i<10;++i){
            ActivePendingChatItem a=new ActivePendingChatItem(i+1+"",i+1+"");
            active.add(a);

        }


        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter=new AdminActivityAdapter(getSupportFragmentManager(),pending,active);
        pager.setAdapter(pagerAdapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }
}
