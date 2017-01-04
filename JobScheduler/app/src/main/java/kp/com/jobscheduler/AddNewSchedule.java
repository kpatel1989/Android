package kp.com.jobscheduler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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

import kp.com.jobscheduler.data.Schedule;

public class AddNewSchedule extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    Button selectStartDate, selectStartTime, selectEndDate, selectEndTime, save;
    EditText startDate,startTime,endDate,endTime;
    boolean isPickerStartTime,isPickerStartDate;
    Schedule schedule;
    Calendar startDt, endDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_schedule);

        schedule = new Schedule();
        startDt = Calendar.getInstance();
        endDt = Calendar.getInstance();

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
            schedule.setStartTime(startDt.getTimeInMillis());
            schedule.setEndTime(endDt.getTimeInMillis());
            ((JobSchedulerApp)getApplication()).saveSchedule(schedule);
            Intent i = new Intent();
            i.putExtra("done","ok");
            this.setResult(RESULT_OK,i);
            this.finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (isPickerStartTime) {
            startDt.set(Calendar.HOUR_OF_DAY,hourOfDay);
            startDt.set(Calendar.MINUTE, minute);
            startTime.setText(hourOfDay + ":" + minute);
        } else {
            endDt.set(Calendar.HOUR_OF_DAY,hourOfDay);
            endDt.set(Calendar.MINUTE,minute);
            endTime.setText(hourOfDay + ":" + minute);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (isPickerStartDate) {
            startDt.set(Calendar.YEAR,year);
            startDt.set(Calendar.MONTH,month);
            startDt.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            startDate.setText(dayOfMonth + "/" + month + "/" + year);
        } else {
            endDt.set(Calendar.YEAR,year);
            endDt.set(Calendar.MONTH,month);
            endDt.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            endDate.setText(dayOfMonth + "/" + month + "/" + year);
        }

    }
}