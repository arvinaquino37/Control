package com.example.control;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Projector303Class extends AppCompatActivity {

    private Button on, off;
    TextView value, projectorstatus303;
    DatabaseReference dref;
    String status, statusProjector;

    Switch switch1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projector303_xml);

        on = findViewById(R.id.on);
        off = findViewById(R.id.off);
//        value = findViewById(R.id.value);
//        switch1 = findViewById(R.id.switch1);
        projectorstatus303 = findViewById(R.id.projectorstatus303);


        dref = FirebaseDatabase.getInstance().getReference();
        // Read from the database
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//                status=dataSnapshot.child("3rd Floor/Classroom/303/Projector").getValue().toString();
//                value.setText(status);

                statusProjector=dataSnapshot.child("3rd Floor/Classroom/303/Projector Status").getValue().toString();
                projectorstatus303.setText(statusProjector);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Projector");

                myRef.setValue(1);
                Toast.makeText(Projector303Class.this, "Projector Off", Toast.LENGTH_SHORT).show();
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Projector");

                myRef.setValue(0);
                Toast.makeText(Projector303Class.this, "Projector On", Toast.LENGTH_SHORT).show();
            }
        });

//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
//                if(isCheck == true){
//                    Toast.makeText(getBaseContext(),"Projector On",Toast.LENGTH_SHORT).show();
//
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Projector");
//
//                    myRef.setValue(0);
//                }
//                else {
//                    Toast.makeText(getBaseContext(),"Projector Off",Toast.LENGTH_SHORT).show();
//
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference myRef = database.getReference("3rd Floor/Classroom/303/Projector");
//
//                    myRef.setValue(1);
//                }
//            }
//        });



    }
}
