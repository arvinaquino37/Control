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

public class Hallway3FClass extends AppCompatActivity {

    TextView thirdflight1, thirdflight2, thirdflight3, thirdflight4, thirdflight5, thirdflight6;
    DatabaseReference dref;
    String status_thirdflight1, status_thirdflight2, status_thirdflight3, status_thirdflight4, status_thirdflight5, status_thirdflight6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hallway3f_xml);

        thirdflight1 = findViewById(R.id.thirdflight1);
        thirdflight2 = findViewById(R.id.thirdflight2);
        thirdflight3 = findViewById(R.id.thirdflight3);
        thirdflight4 = findViewById(R.id.thirdflight4);
        thirdflight5 = findViewById(R.id.thirdflight5);
        thirdflight6 = findViewById(R.id.thirdflight6);

        dref = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);

                status_thirdflight1 = dataSnapshot.child("3rd Floor/Hallway/Light 1").getValue().toString();
                thirdflight1.setText(status_thirdflight1);

                status_thirdflight2 = dataSnapshot.child("3rd Floor/Hallway/Light 2").getValue().toString();
                thirdflight2.setText(status_thirdflight2);

                status_thirdflight3 = dataSnapshot.child("3rd Floor/Hallway/Light 3").getValue().toString();
                thirdflight3.setText(status_thirdflight3);

                status_thirdflight4 = dataSnapshot.child("3rd Floor/Hallway/Light 4").getValue().toString();
                thirdflight4.setText(status_thirdflight4);

                status_thirdflight5 = dataSnapshot.child("3rd Floor/Hallway/Light 5").getValue().toString();
                thirdflight5.setText(status_thirdflight5);

                status_thirdflight6 = dataSnapshot.child("3rd Floor/Hallway/Light 6").getValue().toString();
                thirdflight6.setText(status_thirdflight6);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
}
