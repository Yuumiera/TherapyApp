package com.example.therapyapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class GroupTherapyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private ArrayList<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        roomList = new ArrayList<>();
        roomList.add(new Room(1, 6));
        roomList.add(new Room(2, 6));
        roomList.add(new Room(3, 6));

        // Odaları doluluk oranına göre sırala
        insertionSort(roomList);

        roomAdapter = new RoomAdapter(roomList);
        recyclerView.setAdapter(roomAdapter);
    }

    // Insertion Sort algoritması
    public static void insertionSort(ArrayList<Room> rooms) {
        int n = rooms.size();
        for (int i = 1; i < n; ++i) {
            Room key = rooms.get(i);
            int j = i - 1;

            // Odaları doluluk oranına göre sırala
            while (j >= 0 && rooms.get(j).getCurrentOccupancy() < key.getCurrentOccupancy()) {
                rooms.set(j + 1, rooms.get(j));
                j = j - 1;
            }
            rooms.set(j + 1, key);
        }
    }
}