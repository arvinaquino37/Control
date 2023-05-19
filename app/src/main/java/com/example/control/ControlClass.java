package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ControlClass extends AppCompatActivity {

    private Button onlights, offlights, onaircon, offaircon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_xml);

        onlights = findViewById(R.id.onlights);
        offlights = findViewById(R.id.offlights);

        onaircon = findViewById(R.id.onaircon);
        offaircon = findViewById(R.id.offaircon);

        onlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ControlClass.this, "All Lights: ON", Toast.LENGTH_SHORT).show();
            }
        });

        offlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ControlClass.this, "All Lights: OFF", Toast.LENGTH_SHORT).show();
            }
        });

        onaircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ControlClass.this, "All Aircons: ON", Toast.LENGTH_SHORT).show();
            }
        });

        offaircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ControlClass.this, "All Aircons: OFF", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
