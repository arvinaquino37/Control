package com.example.control;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Aircon303Class extends AppCompatActivity {

    private Button aircon303btn_off, aircon303btn_on, aircon2303btn_on, aircon2303btn_off;
    TextView aircon1303temp, aircon2303temp, roomtemp303, airconrelay303, airconstatus303, voltage, airconpsource303, aircon2psource303, aircon2status303, aircon1303StatusTemp, aircon2303StatusTemp, room303StatusTemp;

    DatabaseReference dref;
    String statusRelay, statusRoomTemp, statusAirconTemp1, statusAirconTemp2, statusAircon, statusVoltage, statusAirconPSource, statusAircon2PSource, statusAircon2, status_aircon1303StatusTemp, status_aircon2303StatusTemp, status_room303StatusTemp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aircon303_xml);

        aircon303btn_off = findViewById(R.id.aircon303btn_off);
        aircon303btn_on = findViewById(R.id.aircon303btn_on);
        aircon2303btn_off = findViewById(R.id.aircon2303btn_off);
        aircon2303btn_on = findViewById(R.id.aircon2303btn_on);
        aircon1303temp = findViewById(R.id.aircon1303temp);
        aircon2303temp = findViewById(R.id.aircon2303temp);
        roomtemp303 = findViewById(R.id.roomtemp303);
//        airconrelay303 = findViewById(R.id.airconrelay303);
        airconstatus303 = findViewById(R.id.airconstatus303);
//        voltage = findViewById(R.id.voltage);
        airconpsource303 = findViewById(R.id.airconpsource303);
        aircon2psource303 = findViewById(R.id.aircon2psource303);
        aircon2status303 = findViewById(R.id.aircon2status303);
        aircon1303StatusTemp = findViewById(R.id.aircon1303StatusTemp);
        aircon2303StatusTemp = findViewById(R.id.aircon2303StatusTemp);
        room303StatusTemp = findViewById(R.id.room303StatusTemp);

        dref = FirebaseDatabase.getInstance().getReference();
        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);

//                statusRelay=dataSnapshot.child("3rd Floor/Classroom/303/Aircon Relay").getValue().toString();
//                airconrelay303.setText(statusRelay);

                statusRoomTemp=dataSnapshot.child("3rd Floor/Classroom/303/Room Temperature (C)").getValue().toString();
                roomtemp303.setText(statusRoomTemp);

                statusAirconTemp1=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Temperature (C)").getValue().toString();
                aircon1303temp.setText(statusAirconTemp1);

                statusAirconTemp2=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon2/Aircon Temperature (C)").getValue().toString();
                aircon2303temp.setText(statusAirconTemp2);

                statusAircon=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Status").getValue().toString();
                airconstatus303.setText(statusAircon);

                statusAirconPSource=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Power Source").getValue().toString();
                airconpsource303.setText(statusAirconPSource);

//                statusVoltage=dataSnapshot.child("3rd Floor/Classroom/303/Voltage").getValue().toString();
//                voltage.setText(statusVoltage);

                statusAircon2=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon2/Aircon Status").getValue().toString();
                aircon2status303.setText(statusAircon2);

                statusAircon2PSource=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Power Source").getValue().toString();
                aircon2psource303.setText(statusAircon2PSource);

                status_aircon1303StatusTemp=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Temp Status").getValue().toString();
                aircon1303StatusTemp.setText(status_aircon1303StatusTemp);

                status_aircon2303StatusTemp=dataSnapshot.child("3rd Floor/Classroom/303/Aircons/Aircon2/Aircon Temp Status").getValue().toString();
                aircon2303StatusTemp.setText(status_aircon2303StatusTemp);

                status_room303StatusTemp=dataSnapshot.child("3rd Floor/Classroom/303/Room Temp Status").getValue().toString();
                room303StatusTemp.setText(status_room303StatusTemp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        aircon303btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Relay");

                myRef.setValue(1);
                Toast.makeText(Aircon303Class.this, "Aircon Off", Toast.LENGTH_SHORT).show();
            }
        });

        aircon303btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Aircons/Aircon1/Aircon Relay");

                myRef.setValue(0);
                Toast.makeText(Aircon303Class.this, "Aircon On", Toast.LENGTH_SHORT).show();
            }
        });

        aircon2303btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Aircons/Aircon2/Aircon Relay");

                myRef.setValue(1);
                Toast.makeText(Aircon303Class.this, "Aircon Off", Toast.LENGTH_SHORT).show();
            }
        });

        aircon2303btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Aircons/Aircon2/Aircon Relay");

                myRef.setValue(0);
                Toast.makeText(Aircon303Class.this, "Aircon On", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
