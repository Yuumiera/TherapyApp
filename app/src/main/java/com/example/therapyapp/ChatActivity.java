package com.example.therapyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;
    private ChatAdapter chatAdapter;
    private ArrayList<Message> messageList;

    private DatabaseReference chatRef;
    private String receiverId;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Firebase veritabanı referansı
        chatRef = FirebaseDatabase.getInstance().getReference().child("messages");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        chatListView = findViewById(R.id.chatListView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, R.layout.message_item, messageList, currentUserId);
        chatListView.setAdapter(chatAdapter);

        receiverId = getIntent().getStringExtra("userId");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                if (message != null && (message.getSenderId().equals(currentUserId) || message.getReceiverId().equals(currentUserId))) {
                    messageList.add(message);
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void sendMessage() {
        String messageText = messageEditText.getText().toString().trim();

        if (!messageText.isEmpty()) {
            String senderId = currentUserId;
            String messageId = chatRef.push().getKey();

            Message message = new Message(senderId, messageText, receiverId);
            Map<String, Object> messageValues = message.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/" + messageId, messageValues);

            chatRef.updateChildren(childUpdates);

            messageEditText.setText("");
        } else {
            Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}