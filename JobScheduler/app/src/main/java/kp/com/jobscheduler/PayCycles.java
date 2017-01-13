package kp.com.jobscheduler;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import kp.com.jobscheduler.data.PayCycle;
import kp.com.jobscheduler.data.Schedule;

public class PayCycles extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    Button addNextPayCycle;
    DatePickerDialog dialog;
    ListView payCyclesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_cycles);
        addNextPayCycle = (Button) findViewById(R.id.addNextPayCycle);
        addNextPayCycle.setOnClickListener(this);

        payCyclesList = (ListView) findViewById(R.id.payCycles);

        ArrayList<PayCycle> payCycles = ((JobSchedulerApp)getApplication()).getPayCycles();
        ArrayList<HashMap<String,String>> payCycleMap = new ArrayList<>();
        for (PayCycle payCycle : payCycles) {
            payCycleMap.add(payCycle.getHashMap());
        }
        String[] from = {PayCycle.START_DAY_HASH_KEY,PayCycle.END_DAY_HASH_KEY};
        int[] to = {R.id.payCycleListStartDate, R.id.payCycleListEndDate};
        SimpleAdapter adapter = new SimpleAdapter(this,payCycleMap,R.layout.pay_cycle_list_item,from,to);
        payCyclesList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(this, this, year, month, day);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        PayCycle payCycle = new PayCycle();
        long lastPayDay = ((JobSchedulerApp)getApplication()).getLastPayDay();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(lastPayDay);
        c.add(Calendar.DAY_OF_MONTH,1);
        payCycle.setStartDay(c.getTimeInMillis());
        c.clear();
        c.set(year,month,dayOfMonth,23,59,59);
        payCycle.setEndDay(c.getTimeInMillis());
        ((JobSchedulerApp)getApplication()).savePayCycle(payCycle);
        recreate();
    }
}
