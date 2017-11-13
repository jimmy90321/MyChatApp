package com.example.jimmy.mychatapp.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jimmy.mychatapp.R;
import com.example.jimmy.mychatapp.common.BaseMessage;

import java.util.List;

public class MessageListActivity_unfinished extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageListAdapter_unfinished mMessageAdapter;
    private EditText etMessage;
    private ImageButton btnSend;
    private View viewChatbox;
    private List<BaseMessage> messageList; //TODO : get all messages in chatRoom as messageList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        getView();
    }

    private void getView() {
        etMessage = (EditText)findViewById(R.id.et_chatbox);
        btnSend = (ImageButton)findViewById(R.id.btn_send);
        viewChatbox = findViewById(R.id.layout_chatbox);
        mMessageRecycler = (RecyclerView)findViewById(R.id.recyclerview_message_list);
        mMessageAdapter = new MessageListAdapter_unfinished(this,messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }

    public void sendMessage(View view) {

        String postResult = null;

        String mMessage = etMessage.getText().toString();
        if(mMessage != null){
            try {
                postResult = new sendMessageTask().execute(mMessage).get();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(postResult != null){

            }
        }

    }
}
