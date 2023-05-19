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

public class Cr2FClass extends AppCompatActivity {

    private Button cr2_on, cr2_off;
    TextView cr2ndlight, cr2readinglight;
    DatabaseReference dref;
    String status, statusreading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cr2f_xml);

        cr2_on= findViewById(R.id.cr2_on);
        cr2_off = findViewById(R.id.cr2_off);
        cr2ndlight = findViewById(R.id.cr2ndlight);
        cr2readinglight = findViewById(R.id.cr2readinglight);

        dref = FirebaseDatabase.getInstance().getReference();

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                status=dataSnapshot.child("2nd Floor/CR/Light").getValue().toString();
                cr2ndlight.setText(status);

                statusreading=dataSnapshot.child("2nd Floor/CR/Light Reading").getValue().toString();
                cr2readinglight.setText(statusreading);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        cr2_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("2nd Floor/CR/Light");

                myRef.setValue(0);
                Toast.makeText(Cr2FClass.this, "CR Lights are On", Toast.LENGTH_SHORT).show();
            }
        });

        cr2_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("2nd Floor/CR/Light");

                myRef.setValue(1);
                Toast.makeText(Cr2FClass.this, "CR Lights are Off", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
