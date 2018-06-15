package kp.com.jobscheduler;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import kp.com.jobscheduler.data.PayCycle;


/**
 * A simple {@link Fragment} subclass.
 */
public class PayCycleFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button addNextPayCycle;
    DatePickerDialog dialog;
    ListView payCyclesList;
    SimpleAdapter listAdapter;

    public PayCycleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_cycle, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addNextPayCycle = (Button) getActivity().findViewById(R.id.addNextPayCycle);
        addNextPayCycle.setOnClickListener(this);

        payCyclesList = (ListView) getActivity().findViewById(R.id.payCycles);

        ArrayList<PayCycle> payCycles = ((JobSchedulerApp)getActivity().getApplication()).getPayCycles();
        ArrayList<HashMap<String,String>> payCycleMap = new ArrayList<>();
        for (PayCycle payCycle : payCycles) {
            payCycleMap.add(payCycle.getHashMap());
        }
        String[] from = {PayCycle.START_DAY_HASH_KEY,PayCycle.END_DAY_HASH_KEY};
        int[] to = {R.id.payCycleListStartDate, R.id.payCycleListEndDate};
        listAdapter = new SimpleAdapter(this.getActivity(),payCycleMap,R.layout.pay_cycle_list_item,from,to);
        payCyclesList.setAdapter(listAdapter);
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
        listAdapter.notifyDataSetChanged();
    }
}
