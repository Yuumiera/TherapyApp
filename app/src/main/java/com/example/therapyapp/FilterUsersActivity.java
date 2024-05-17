package com.example.therapyapp;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FilterUsersActivity extends AppCompatActivity {

    private Spinner disorderSpinner;
    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;
    private List<User> userList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_users);

        disorderSpinner = findViewById(R.id.disorderSpinner);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);

        db = FirebaseFirestore.getInstance();
        userList = new ArrayList<>();
        usersAdapter = new UsersAdapter(userList);

        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.setAdapter(usersAdapter);

        // Bozuklukları (disorders) doldur
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.disorders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disorderSpinner.setAdapter(adapter);

        disorderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDisorder = parent.getItemAtPosition(position).toString();
                filterUsersByDisorder(selectedDisorder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        });
    }

    private void filterUsersByDisorder(String disorder) {
        db.collection("users")
                .whereEqualTo("disorder", disorder)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@NonNull QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Toast.makeText(FilterUsersActivity.this, "Error loading users", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        userList.clear();
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            User user = document.toObject(User.class);
                            userList.add(user);
                        }
                        usersAdapter.notifyDataSetChanged();
                    }
                });
    }
}
