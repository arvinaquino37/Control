package com.example.control;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private long backPressedTime;
    private Toast backToast;

    private LinearLayout status, projector, history, control, classroom, secondfloor, thirdfloor, fourthfloor;
    Button icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
//        projector = findViewById(R.id.projector);
//        control = findViewById(R.id.control);
//        classroom = findViewById(R.id.classroom);

        icon = findViewById(R.id.icon);

        secondfloor = findViewById(R.id.secondfloor);
        thirdfloor = findViewById(R.id.thirdfloor);
        fourthfloor = findViewById(R.id.fourthfloor);
        history = findViewById(R.id.history);


        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StairsClass.class);
                startActivity(intent);
            }
        });

//        projector.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Projector303Class.class);
//                startActivity(intent);
//            }
//        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryClass.class);
                startActivity(intent);
            }
        });

//        control.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ControlClass.class);
//                startActivity(intent);
//            }
//        });

//        classroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Classroom2ndClass.class);
//                startActivity(intent);
//            }
//        });

//        icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                   final PopupMenu popupMenu = new PopupMenu(MainActivity.this, icon);
//                   popupMenu.getMenuInflater().inflate(R.menu.logoutmenu, popupMenu.getMenu());
//
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
////                        public boolean onMenuItemClick(MenuItem item) {
////                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                            startActivity(intent);
////                            Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
////                            return true;
////                        }
////                    });
//
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menuItem) {
//                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                            startActivity(intent);
//
//                            return true;
//                        }
//                    });
//
//                    popupMenu.show();
//
//            }
//        });

        secondfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondFloorClass.class);
                startActivity(intent);
            }
        });

        thirdfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdFloorClass.class);
                startActivity(intent);
            }
        });

        fourthfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FourthFloorClass.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return;
        }else {
            backToast = Toast.makeText(getBaseContext(), "Press again to Exit App/Logout", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();

    }


    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.logoutmenu);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId() ) {
            case R.id.editaccount:
                Intent intent = new Intent(MainActivity.this, EditAccountClass.class);
                startActivity(intent);
                return true;
            case R.id.logout:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Warning!");
                alert.setMessage("Are you sure do you want to Logout?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this,"yes", Toast.LENGTH_SHORT).show();

                        Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent2);

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this,"No", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

//                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent2);
                return true;

                default:
                    return false;

        }
    }
}
