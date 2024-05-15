package com.example.therapyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private ArrayList<Room> roomList;

    public RoomAdapter(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.roomId.setText("Oda " + room.getRoomId());
        holder.occupancy.setText("Doluluk Oranı: " + room.getCurrentOccupancy() + "/" + room.getCapacity());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomId;
        TextView occupancy;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomId = itemView.findViewById(R.id.roomId);
            occupancy = itemView.findViewById(R.id.occupancy);
        }
    }
}