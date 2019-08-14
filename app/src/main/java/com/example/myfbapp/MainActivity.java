package com.example.myfbapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText mName , mEmail,mPassword;
    Button mBtnSave , mBtnView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.editName);
        mEmail =findViewById(R.id.editEmail);
        mPassword= findViewById(R.id.editPassword);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnView = findViewById(R.id.btnView);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Saving");
        dialog.setMessage("Please wait...");

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //receive data
                String jina= mName.getText().toString();
                String arafa= mEmail.getText().toString();
                String siri= mPassword.getText().toString();

                long Time = System.currentTimeMillis();
                String timeconvert = String.valueOf(Time);

                //Create a Table/Child
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+timeconvert);
                //create the columns in the table and store data
                Item x = new Item(jina , arafa, siri ,timeconvert);
                //store the data
                dialog.show();
                ref.setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User saved Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "User not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tembea = new Intent(MainActivity.this  , usersActivity.class);
                startActivity(tembea);
            }
        });
    }
}
