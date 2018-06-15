package kp.com.jobscheduler;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import kp.com.jobscheduler.data.PayCycle;
import kp.com.jobscheduler.data.Schedule;
import kp.com.jobscheduler.data.WageCalculator;

public class CalculatePay extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button selectStartDate, selectEndDate, calculatePay;
    EditText startDate,endDate;
    boolean isPickerStartDate;
    PayCycle payCycle;
    Calendar startDt, endDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_pay);

        selectStartDate = (Button) findViewById(R.id.selectPayCycleStartDate);
        selectStartDate.setOnClickListener(this);

        startDate = (EditText) findViewById(R.id.payCycleStartDate);
        endDate = (EditText) findViewById(R.id.payCycleEndDate);

        selectEndDate = (Button) findViewById(R.id.selectPayCycleEndDate);
        selectEndDate.setOnClickListener(this);

        calculatePay = (Button) findViewById(R.id.calculatePay);
        calculatePay.setOnClickListener(this);

        payCycle = new PayCycle();

        startDt = Calendar.getInstance();
        endDt = Calendar.getInstance();
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;
        switch (v.getId()) {
            case R.id.selectPayCycleStartDate:
                isPickerStartDate = true;
                // Create a new instance of DatePickerDialog and return it
                dialog = new DatePickerDialog(this, this, year, month, day);
                dialog.show();
                break;
            case R.id.selectPayCycleEndDate:
                isPickerStartDate = false;
                // Create a new instance of DatePickerDialog and return it
                dialog = new DatePickerDialog(this, this, year, month, day);
                dialog.show();
                break;
            case R.id.calculatePay:
                payCycle.setStartDay(startDt.getTimeInMillis());
                payCycle.setEndDay(endDt.getTimeInMillis());
                payCycle.setShifts(((JobSchedulerApp)getApplication()).database.getSchedulesInPayCycle(payCycle));
                long totalTime = payCycle.getTotalTime();
                double hours = Math.floor(totalTime / (1000 * 60 * 60));
                double minutes = Math.ceil((totalTime - (hours * 1000 * 60 * 60))/ (1000 * 60));
                double totalWage = payCycle.calculatePay();
                Toast.makeText(this,hours + " hours , " + minutes + " minutes. Total Wage = " + totalWage,Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (isPickerStartDate) {
            startDt.set(year,month,dayOfMonth,0,0,0);
            startDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        } else {
            endDt.set(year,month,dayOfMonth,23,59,59);
            endDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    }
}
