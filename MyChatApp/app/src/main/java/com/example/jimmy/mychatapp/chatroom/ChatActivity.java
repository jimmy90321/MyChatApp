package com.example.jimmy.mychatapp.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jimmy.mychatapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView listview;
    private View btn_send;
    private EditText et_type;
    boolean myMassage = true;
    private List<ChatBubble> chatBubbles;
    private ArrayAdapter<ChatBubble> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatBubbles = new ArrayList<>();

        listview = (ListView)findViewById(R.id.list_msg);
        btn_send = findViewById(R.id.btn_send);
        et_type = (EditText)findViewById(R.id.et_type_in);

        //set ListView adapter
        adapter = new MessageAdapter(this, R.layout.left_chat_bubble, chatBubbles);
        listview.setAdapter(adapter);

        //event for btn_send
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatBubble chatBubble = new ChatBubble(et_type.getText().toString(),myMassage);
                chatBubbles.add(chatBubble);
                adapter.notifyDataSetChanged();
                et_type.setText("");
            }
        });
    }
}
