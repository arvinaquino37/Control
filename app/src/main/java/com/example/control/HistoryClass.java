package com.example.control;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;

public class HistoryClass extends AppCompatActivity {

    TextView fromDate, toDate;
     DatePickerDialog.OnDateSetListener mDateSetListener;
     DatePickerDialog.OnDateSetListener mDateSetListenerr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_xml);

        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(HistoryClass.this, android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(HistoryClass.this, android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListenerr, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)  {  //   year/month/day
//                Log.d(TAG, "on DAte Set: Date: " + i + "/" + i1 + "/" + i2);
                month = month + 1;
                System.out.println("on DAte Set: Date: MM/DD/YYYY: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                fromDate.setText(date);

            }
        };

        mDateSetListenerr = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)  {  //   year/month/day
//                Log.d(TAG, "on DAte Set: Date: " + i + "/" + i1 + "/" + i2);
                month = month + 1;
                System.out.println("on DAte Set: Date: MM/DD/YYYY: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;

                toDate.setText(date);
            }
        };






    }
}
