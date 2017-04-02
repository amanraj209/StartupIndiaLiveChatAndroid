package com.example.android.startupindialivechat.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.startupindialivechat.R;
import com.example.android.startupindialivechat.model.ActivePendingChatItem;
import com.example.android.startupindialivechat.model.ChatMessage;

import java.util.List;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class AdminMessageAdapter extends ArrayAdapter<ActivePendingChatItem> {
    private Activity activity;
    private List<ActivePendingChatItem> messages;

    public AdminMessageAdapter(Activity context, int resource, List<ActivePendingChatItem> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
        ActivePendingChatItem chatitem= getItem(position);
        int viewType = getItemViewType(position);

       layoutResource=R.layout.list_item_chatlist;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set message content
        holder.statement.setText(chatitem.getProblem());
        holder.number.setText(chatitem.getNumber());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 1;
    }



    private class ViewHolder {
        private TextView number;
        private TextView statement;

        public ViewHolder(View v) {
            number = (TextView) v.findViewById(R.id.tv_number);
            statement = (TextView) v.findViewById(R.id.tv_statement);


        }
    }
}
