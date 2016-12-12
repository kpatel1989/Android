package kp.com.jobscheduler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class AddNewSchedule extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

//    DatePicker datePicker;
    Button selectStartDate, selectStartTime, selectEndDate, selectEndTime, save;
    EditText startDate,startTime,endDate,endTime;
    boolean isPickerStartTime,isPickerStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_schedule);

        selectStartDate = (Button) findViewById(R.id.selectStartDate);
        selectStartDate.setOnClickListener(this);

        selectStartTime = (Button) findViewById(R.id.selectStartTime);
        selectStartTime.setOnClickListener(this);

        startDate = (EditText) findViewById(R.id.startDate);
        startTime = (EditText) findViewById(R.id.startTime);

        selectEndDate = (Button) findViewById(R.id.selectEndDate);
        selectEndDate.setOnClickListener(this);

        selectEndTime = (Button) findViewById(R.id.selectEndTime);
        selectEndTime.setOnClickListener(this);

        endDate = (EditText) findViewById(R.id.endDate);
        endTime = (EditText) findViewById(R.id.endTime);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == selectStartDate) {
            isPickerStartDate = true;
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog  dialog = new DatePickerDialog(this, this, year, month, day);
            dialog.show();
        } else if (v == selectStartTime) {
            isPickerStartTime = true;
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            TimePickerDialog newFragment = new TimePickerDialog(this, this, hour, minute,
                    DateFormat.is24HourFormat(this));

            newFragment.show();
        } else if (v == selectEndDate) {
            isPickerStartDate = false;
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog  dialog = new DatePickerDialog(this, this, year, month, day);
            dialog.show();
        } else if (v == selectEndTime) {
            isPickerStartTime = false;
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            TimePickerDialog newFragment = new TimePickerDialog(this, this, hour, minute,
                    DateFormat.is24HourFormat(this));

            newFragment.show();
        } else {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            String dates = prefs.getString("dates","");
            dates += "," + startDate.getText() + "-" + startTime.getText();
            editor.putString("dates",dates);
            this.finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (isPickerStartTime) {
            startTime.setText(hourOfDay + ":" + minute);
        } else {
            endTime.setText(hourOfDay + ":" + minute);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (isPickerStartDate) {
            startDate.setText(dayOfMonth + "/" + month + "/" + year);
        } else {
            endDate.setText(dayOfMonth + "/" + month + "/" + year);
        }

    }
}