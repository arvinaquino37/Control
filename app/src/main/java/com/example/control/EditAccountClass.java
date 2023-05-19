package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccountClass extends AppCompatActivity {

    LinearLayout addaccount, deleteaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaccount_xml);

        addaccount = findViewById(R.id.addaccount);
        deleteaccount = findViewById(R.id.deleteaccount);

        addaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAccountClass.this, AddAccountClass.class);
                startActivity(intent);
            }
        });

        deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAccountClass.this, DeleteAccountClass.class);
                startActivity(intent);
            }
        });


    }



}
