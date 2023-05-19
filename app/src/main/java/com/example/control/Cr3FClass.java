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

public class Cr3FClass extends AppCompatActivity {

    private Button cr3_on, cr3_off;
    TextView cr3rdlight, cr3readinglight;
    DatabaseReference dref;
    String status, statusreading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cr3f_xml);

        cr3_on= findViewById(R.id.cr3_on);
        cr3_off = findViewById(R.id.cr3_off);
        cr3rdlight = findViewById(R.id.cr3rdlight);
        cr3readinglight = findViewById(R.id.cr3readinglight);

        dref = FirebaseDatabase.getInstance().getReference();

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                status=dataSnapshot.child("3rd Floor/CR/Light").getValue().toString();
                cr3rdlight.setText(status);

                statusreading=dataSnapshot.child("3rd Floor/CR/Light Reading").getValue().toString();
                cr3readinglight.setText(statusreading);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        cr3_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/CR/Light");

                myRef.setValue(0);
                Toast.makeText(Cr3FClass.this, "CR Lights are On", Toast.LENGTH_SHORT).show();
            }
        });

        cr3_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/CR/Light");

                myRef.setValue(1);
                Toast.makeText(Cr3FClass.this, "CR Lights are Off", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
