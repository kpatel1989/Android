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
    Button selectDate, selectTime, save;
    EditText date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_schedule);
//        datePicker = (DatePicker) findViewById(R.id.datePicker);
//        Log.d("Date", new Date().toString() + "");
//        datePicker.init(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),this);
        selectDate = (Button) findViewById(R.id.selectDate);
        selectDate.setOnClickListener(this);

        selectTime = (Button) findViewById(R.id.selectTime);
        selectTime.setOnClickListener(this);

        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == selectDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog  dialog = new DatePickerDialog(this, this, year, month, day);
            dialog.show();
        } else if (v == selectTime) {
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
            dates += "," + date.getText() + "-" + time.getText();
            editor.putString("dates",dates);
            this.finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText(dayOfMonth + "/" + month + "/" + year);
    }
}