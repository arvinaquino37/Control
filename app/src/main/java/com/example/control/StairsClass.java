package com.example.control;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StairsClass extends AppCompatActivity {

    TextView tempC, tempF, humidity, lightintensity, currentReading, stairslight1, stairslight2;
    DatabaseReference dref;
    String statusC, statusF, statusHumidity,statuslight, statusCurrentReading, statusLight1, statusLight2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stairs_xml);

//        tempC = findViewById(R.id.tempC);
//        tempF = findViewById(R.id.tempF);
//        humidity = findViewById(R.id.humidity);
//        lightintensity = findViewById(R.id.lightintensity);
//        currentReading = findViewById(R.id.currentReading);
        stairslight1 = findViewById(R.id.stairslight1);
        stairslight2 = findViewById(R.id.stairslight2);

        dref = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//                statusC = dataSnapshot.child("Temperature (C)").getValue().toString();
//                tempC.setText(statusC);
//
//                statusF = dataSnapshot.child("Temperature (F)").getValue().toString();
//                tempF.setText(statusF);
//
//                statusHumidity = dataSnapshot.child("Humidity").getValue().toString();
//                humidity.setText(statusHumidity);
//
//                statuslight = dataSnapshot.child("3rd Floor/Classroom/303/Light Current Reading 1").getValue().toString();
//                lightintensity.setText(statuslight);
//
//                statusCurrentReading = dataSnapshot.child("3rd Floor/Classroom/303/Aircon1 Current Reading").getValue().toString();
//                currentReading.setText(statusCurrentReading);

                statusLight1 = dataSnapshot.child("Stairs/Light 1").getValue().toString();
                stairslight1.setText(statusLight1);

                statusLight2 = dataSnapshot.child("Stairs/Light 2").getValue().toString();
                stairslight2.setText(statusLight2);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}
