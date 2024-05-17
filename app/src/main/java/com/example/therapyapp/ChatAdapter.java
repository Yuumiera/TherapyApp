package com.example.therapyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<Message> {
    private Context context;
    private int resource;
    private List<Message> messages;
    private String currentUserId;

    public ChatAdapter(@NonNull Context context, int resource, @NonNull List<Message> messages, String currentUserId) {
        super(context, resource, messages);
        this.context = context;
        this.resource = resource;
        this.messages = messages;
        this.currentUserId = currentUserId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        TextView senderTextView = convertView.findViewById(R.id.senderTextView);

        Message message = messages.get(position);

        messageTextView.setText(message.getMessageText());
        if (message.getSenderId().equals(currentUserId)) {
            senderTextView.setText("You");
        } else {
            senderTextView.setText("Other");
        }

        return convertView;
    }
}