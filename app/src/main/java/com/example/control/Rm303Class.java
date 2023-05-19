package com.example.control;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rm303Class extends AppCompatActivity {

    private LinearLayout projector303, aircon303, light303;
    TextView light1reading303, light2reading303;

    DatabaseReference dref;
    String statuslight1reading303, statuslight2reading303;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rm303_xml);

        projector303 = findViewById(R.id.projector303);
        aircon303 = findViewById(R.id.aircon303);
        light303 = findViewById(R.id.light303);
        light1reading303 = findViewById(R.id.light1reading303);
        light2reading303 = findViewById(R.id.light2reading303);

        dref = FirebaseDatabase.getInstance().getReference();
        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                statuslight1reading303=dataSnapshot.child("3rd Floor/Classroom/303/Lights/Light1/Light Group 1 Status").getValue().toString();
                light1reading303.setText(statuslight1reading303);

                statuslight2reading303=dataSnapshot.child("3rd Floor/Classroom/303/Lights/Light2/Light Group 2 Status").getValue().toString();
                light2reading303.setText(statuslight2reading303);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        projector303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rm303Class.this, Projector303Class.class);
                startActivity(intent);
            }
        });

        aircon303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rm303Class.this, Aircon303Class.class);
                startActivity(intent);
            }
        });

        light303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Rm303Class.this, StairsClass.class);
//                startActivity(intent);
            }
        });

    }
}
