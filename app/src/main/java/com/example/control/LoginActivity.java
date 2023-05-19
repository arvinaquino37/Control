package com.example.control;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class LoginActivity extends AppCompatActivity {

    Button login;
    TextView email1, password1;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar1;
    private FirebaseUser currentUser;

//    public DatabaseReference privilege;

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            //already login user
//            Intent intent = new Intent(MainActivity.this, DashboardClass.class);
//            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);
        progressBar1 = findViewById(R.id.progressBar);
        progressBar1.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                AllowUserToLogin();

            }
        });


    }

    private void AllowUserToLogin() {

        final String email = email1.getText().toString();
        String password = password1.getText().toString();
        final String id = FirebaseAuth.getInstance().getUid();
//        final DatabaseReference privilege = getInstance().getReference("privilege");
//       privilege = getInstance().getReference("Users/privilege");


        if(email.isEmpty()){
            email1.setError("Email Required!");
            email1.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Enter a valid Email");
            email1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            password1.setError("Input Password");
            password1.requestFocus();
            return;
        }
        if (password.length() < 6){
            password1.setError("Password should atleast 6 long");
            password1.requestFocus();
            return;
        }
        else {
            progressBar1.setVisibility(View.VISIBLE);

            //--------------------------------------------------------------------------//
            if(!isConnected(LoginActivity.this)) buildDialog(LoginActivity.this).show();
            else {
//                Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
//                setContentView(R.layout.activity_main);
            }


            //--------------------------------------------------------------------------//

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        //--------------------------------------------------------------------------//
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressBar1.setVisibility(View.GONE);
                            if(task.isSuccessful()){


                                //-------------------PRIVILEGE------------------------------------
                                final String FGS = "FGS";
                                final String Maintenance = "Maintenance";
                                final String Prof = "Prof";

//                                FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
//                                DatabaseReference myRef = mFirebaseDatabase.getReference();
//                                myRef.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });

                                DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
                                final DatabaseReference usersref = rootref.child("Users");
//                                usersref.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
//                                            String privilege = singleSnapshot.child("privilege").getValue().toString();
////                                            mDatabase.child("users").child(UserID).child("profile").child("username").getValue().toString();
//                                            if(email.equals(FGS.matches(privilege))){
//                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }
//                                            else if(email.equals(maintenance.matches(privilege))){
//                                                Intent intent = new Intent(LoginActivity.this, MaintenanceClass.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }
//                                            else if(email.equals(Prof.matches(privilege))){
//                                                Intent intent = new Intent(LoginActivity.this, ProfClass.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
                                usersref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot data: dataSnapshot.getChildren()){
                                            Artist artist = new Artist();
                                            String privilege;
//                                            artist.setPrivilege(data.child("privilege").getValue(Artist.class).getPrivilege());
//                                            String privilege = data.child("privilege").getValue().toString();
                                            System.out.println(email);
                                            System.out.println(artist);
//                                            System.out.println(privilege);

//                                            if(artist.equals("FGS")){
//                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }
//                                            else if(artist.equals("Maintenance")){
//                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }
//                                            else if(artist.equals(Prof)){
//                                                Intent intent = new Intent(LoginActivity.this, ProfClass.class);
//                                                startActivity(intent);
//                                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                //----------------------------------------------------------------

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressBar1.setVisibility(View.GONE);
                                String message = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //--------------------------------------------------------------------------//
                    });



        }

    }



    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please turn on your Wifi or mobile data.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }

    @Override
    public void onBackPressed() {

//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            backToast.cancel();
//            super.onBackPressed();
//            return;
//        }else {
//            backToast = Toast.makeText(getBaseContext(), "Press again to Exit App/Logout", Toast.LENGTH_SHORT);
//            backToast.show();
//        }
//
//        backPressedTime = System.currentTimeMillis();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);



    }
}
