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

public class SentMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText,timeText;
    public SentMessageHolder(View itemView) {
        super(itemView);

        messageText = (TextView)itemView.findViewById(R.id.tv_sent_message);
        timeText = (TextView)itemView.findViewById(R.id.tv_sent_time);
    }

    public void Bind(UserMessage message){
        messageText.setText(message.getMessage());

        timeText.setText(DateUtils.formatElapsedTime(message.getCreatedAt()));
    }
}
