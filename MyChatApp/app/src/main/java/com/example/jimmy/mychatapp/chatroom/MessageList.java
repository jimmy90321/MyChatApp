package com.example.jimmy.mychatapp.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.jimmy.mychatapp.R;

/**
 * Created by jimmy on 2017/11/12.
 */

public class MessageList extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private EditText etMessage;
    private View viewChatbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        getView();
    }

    private void getView() {
        etMessage = (EditText)findViewById(R.id.et_chatbox);
        viewChatbox = findViewById(R.id.layout_chatbox);
        mMessageRecycler = (RecyclerView)findViewById(R.id.recyclerview_message_list);
    }

    public void sendMessage(View view) {

        String postResult = null;

        String mMessage = etMessage.getText().toString();
        if(mMessage.trim().length() == 0){
            try {
                postResult = new sendMessageTask().execute(mMessage).get();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(postResult != null){
            etMessage.setText("");
            }
        }
    }
}
