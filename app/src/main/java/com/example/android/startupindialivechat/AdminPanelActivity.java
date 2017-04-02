package com.example.android.startupindialivechat;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.startupindialivechat.Adapters.AdminActivityAdapter;

public class AdminPanelActivity extends AppCompatActivity {
    ViewPager pager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter=new AdminActivityAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }
}
