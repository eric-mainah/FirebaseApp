package com.example.myfbapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class usersActivity extends AppCompatActivity {
ListView list;
CustomAdapter adapter;
ArrayList <Item>users;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        list = findViewById(R.id.myList);
        users = new ArrayList<>();
        adapter = new CustomAdapter(this , users);
        dialog = new ProgressDialog(this );
        dialog.setTitle("Loading ");
        dialog.setMessage("Please wait ....");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    Item x = snap.getValue(Item.class);
                    users.add(x);
                    Collections.reverse(users);
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(usersActivity.this, "Please contact your service provider for assistance", Toast.LENGTH_SHORT).show();
            }
        });

        list.setAdapter(adapter);
    }
}
