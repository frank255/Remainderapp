package com.example.remainderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class dialogbox extends AppCompatActivity {
    Button btnSave, btnDate, btnTime;
    EditText editTitle;
    String timeTonotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogbox);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDate = (Button) findViewById(R.id.btnDate);                                             //assigned all the material reference to get and set data
        btnTime = (Button) findViewById(R.id.btnTime);
        editTitle = (EditText) findViewById(R.id.editTitle);
        Button btncancel = (Button) findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dialogbox.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();                                                                       //when we click on the choose time button it calls the select time method
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }                                        //when we click on the choose date button it calls the select date method
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString().trim();                               //access the data form the input field
                String date = btnDate.getText().toString().trim();                                 //access the date form the choose date button
                String time = btnTime.getText().toString().trim();                                 //access the time form the choose time button

                if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter text", Toast.LENGTH_SHORT).show();   //shows the toast if input field is empty
                } else {
                    if (time.equals("time") || date.equals("date")) {                                               //shows toast if date and time are not selected
                        Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();
                    } else {
                       // processinsert(title, date, time);

                    }
                }


            }
        });



    }



    private void selectTime() {                                                                     //this method performs the time picker task
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;                                                        //temp variable to store the time to set alarm
                btnTime.setText(FormatTime(i, i1));                                                //sets the button text as selected time
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    private void selectDate() {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btnDate.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {                                                //this method converts the time into 12hr farmat and assigns am or pm

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }



}

