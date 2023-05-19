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

public class Hallway2FClass extends AppCompatActivity {

    TextView secondflight1, secondflight2, secondflight3, secondflight4, secondflight5, secondflight6;
    DatabaseReference dref;
    String status_secondflight1, status_secondflight2, status_secondflight3, status_secondflight4, status_secondflight5, status_secondflight6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hallway2f_xml);

        secondflight1 = findViewById(R.id.secondflight1);
        secondflight2 = findViewById(R.id.secondflight2);
        secondflight3 = findViewById(R.id.secondflight3);
        secondflight4 = findViewById(R.id.secondflight4);
        secondflight5 = findViewById(R.id.secondflight5);
        secondflight6 = findViewById(R.id.secondflight6);

        dref = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);


                status_secondflight1 = dataSnapshot.child("2nd Floor/Hallway/Light 1").getValue().toString();
                secondflight1.setText(status_secondflight1);

                status_secondflight2 = dataSnapshot.child("2nd Floor/Hallway/Light 2").getValue().toString();
                secondflight2.setText(status_secondflight2);

                status_secondflight3 = dataSnapshot.child("2nd Floor/Hallway/Light 3").getValue().toString();
                secondflight3.setText(status_secondflight3);

                status_secondflight4 = dataSnapshot.child("2nd Floor/Hallway/Light 4").getValue().toString();
                secondflight4.setText(status_secondflight4);

                status_secondflight5 = dataSnapshot.child("2nd Floor/Hallway/Light 5").getValue().toString();
                secondflight5.setText(status_secondflight5);

                status_secondflight6 = dataSnapshot.child("2nd Floor/Hallway/Light 6").getValue().toString();
                secondflight6.setText(status_secondflight6);





            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
}
