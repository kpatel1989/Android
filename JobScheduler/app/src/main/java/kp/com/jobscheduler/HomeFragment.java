package kp.com.jobscheduler;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import kp.com.jobscheduler.data.PayCycle;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    DatePickerDialog dialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        calculatePay();

    }

    private void calculatePay() {
        PayCycle lastPayCycle = ((JobSchedulerApp)(getActivity().getApplication())).getLastPayCycle();
        long lastPayDay = lastPayCycle.getEndDay();
        Calendar today = Calendar.getInstance();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),23,59,59);
        long currentDate = (today).getTimeInMillis();
        if (currentDate >= lastPayDay) {
            Button setNextPayDay = (Button) getView().findViewById(R.id.setNextPayDay);
            setNextPayDay.setVisibility(View.VISIBLE);
            setNextPayDay.setOnClickListener(this);
        } else {
            //calculate pay.
            lastPayCycle.setShifts(((JobSchedulerApp)getActivity().getApplication()).database.getSchedulesInPayCycle(lastPayCycle));
            double wage = lastPayCycle.calculatePay();
            TextView totalHours = (TextView) getActivity().findViewById(R.id.totalHours);
            TextView txtPayDay = (TextView) getActivity().findViewById(R.id.txtPayDay);
            TextView salary = (TextView) getActivity().findViewById(R.id.salary);
            salary.setText("$" + wage);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastPayCycle.getEndDay());
            txtPayDay.setText(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
            long totalTime = lastPayCycle.getTotalTime();
            double hours = Math.floor(totalTime / (1000 * 60 * 60));
            double minutes = Math.ceil((totalTime - (hours * 1000 * 60 * 60))/ (1000 * 60));
            totalHours.setText(hours + " hours & " + minutes + " minutes");
        }
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(this.getActivity(), this, year, month, day);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        PayCycle payCycle = new PayCycle();
        long lastPayDay = ((JobSchedulerApp)getActivity().getApplication()).getLastPayDay();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(lastPayDay);
        c.add(Calendar.DAY_OF_MONTH,1);
        payCycle.setStartDay(c.getTimeInMillis());
        c.clear();
        c.set(year,month,dayOfMonth,23,59,59);
        payCycle.setEndDay(c.getTimeInMillis());
        ((JobSchedulerApp)getActivity().getApplication()).savePayCycle(payCycle);
    }
}
