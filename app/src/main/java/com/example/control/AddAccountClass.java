package com.example.control;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAccountClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        Button createaccount;
        EditText email2, password2;
        Spinner privileges;
        ProgressBar progressBar2;
        DatabaseReference databaseArtists;
        TextView show;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            //already login user

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addaccount_xml);

        createaccount = findViewById(R.id.createaccount);
        email2 = findViewById(R.id.email2);
        password2 = findViewById(R.id.password2);
        privileges = findViewById(R.id.privileges);
        progressBar2 = findViewById(R.id.progressBar2);
        show = findViewById(R.id.show); // toggle show/

//        show.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        databaseArtists = FirebaseDatabase.getInstance().getReference("Users");


        password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        privileges.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.privileges, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        privileges.setAdapter(adapter);
        privileges.setOnItemSelectedListener(this);



        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AddAccountClass.this,"Added Successfully" , Toast.LENGTH_SHORT).show();

                registerUser();

            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show.getText() == "SHOW"){
                    show.setText("HIDE");
                    password2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password2.setSelection(password2.length());
                }
                else {
                    show.setText("SHOW");
                    password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password2.setSelection(password2.length());
                }
            }
        });


    }



    private void registerUser(){
        final String email = email2.getText().toString().trim();
        final String password = password2.getText().toString().trim();
        final String privilege = privileges.getSelectedItem().toString();


        if(email.isEmpty()){
            email2.setError("Email Required!");
            email2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email2.setError("Enter a valid Email");
            email2.requestFocus();
            return;
        }


        if(password.isEmpty()){
            password2.setError("Input Password");
            password2.requestFocus();
            show.setVisibility(View.GONE);
            return;
        }
        if (password.length() < 6){
            password2.setError("Password must be >5 characters");
            password2.requestFocus();
            show.setVisibility(View.VISIBLE);
            return;
        }


//        if(!TextUtils.isEmpty(email)){


//            databaseArtists.child(id).setValue(artist);

//            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
//        }

        progressBar2.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //we will store additional fields in firebase database
//                            final String id = databaseArtists.push().getKey();
                            final String id = FirebaseAuth.getInstance().getUid();
                            Artist user = new Artist(

                                    email,
                                    password,
                                    privilege, id

                            );
//                            databaseArtists.child(id).setValue(user);

                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(id)
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar2.setVisibility(View.GONE);
                                    if (task.isSuccessful()){

                                        Toast.makeText(AddAccountClass.this, "Registered Successfully",Toast.LENGTH_SHORT).show();

                                    }
                                    else {

                                        Toast.makeText(AddAccountClass.this, "Invalid",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }
                        else{
                            progressBar2.setVisibility(View.GONE);
                            Toast.makeText(AddAccountClass.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }





}
