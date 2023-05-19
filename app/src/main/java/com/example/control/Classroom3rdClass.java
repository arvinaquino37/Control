package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Classroom3rdClass extends AppCompatActivity {

    private LinearLayout rm303;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom3rd_xml);

        rm303 = findViewById(R.id.rm303);

        rm303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Classroom3rdClass.this, Rm303Class.class);
                startActivity(intent);
            }
        });


    }
}
