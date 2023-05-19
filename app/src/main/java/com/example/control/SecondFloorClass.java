package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondFloorClass extends AppCompatActivity {

    private LinearLayout classroom, hallway, cr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondfloor_xml);

        classroom = findViewById(R.id.classroom);
        hallway = findViewById(R.id.hallway);
        cr = findViewById(R.id.cr);

        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondFloorClass.this, Classroom2ndClass.class);
                startActivity(intent);
            }
        });

        hallway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondFloorClass.this, Hallway2FClass.class);
                startActivity(intent);
            }
        });

        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondFloorClass.this, Cr2FClass.class);
                startActivity(intent);
            }
        });

    }
}
