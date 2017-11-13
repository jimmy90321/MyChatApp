package com.example.jimmy.mychatapp.message;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.jimmy.mychatapp.R;
import com.example.jimmy.mychatapp.common.UserMessage;

/**
 * Created by jimmy on 2017/11/12.
 */

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

    TextView nameText,messageText,timeText;

    public ReceivedMessageHolder(View itemView) {
        super(itemView);

        nameText = (TextView)itemView.findViewById(R.id.tv_received_name);
        messageText = (TextView)itemView.findViewById(R.id.tv_received_message);
        timeText = (TextView)itemView.findViewById(R.id.tv_received_time);
    }

    public void Bind(UserMessage message){
        messageText.setText(message.getMessage());

        timeText.setText(DateUtils.formatElapsedTime(message.getCreatedAt()));
        nameText.setText(message.getUserName());
    }
}
