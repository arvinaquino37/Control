package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FourthFloorClass extends AppCompatActivity {

    private LinearLayout classroom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthfloor_xml);

        classroom = findViewById(R.id.classroom);

        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthFloorClass.this, Classroom4thClass.class);
                startActivity(intent);
            }
        });

    }
}
