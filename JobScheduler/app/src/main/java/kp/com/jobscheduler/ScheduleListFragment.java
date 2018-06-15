package kp.com.jobscheduler;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import kp.com.jobscheduler.data.Schedule;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleListFragment extends Fragment {

    ListView timeLogList;

    public ScheduleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        timeLogList = (ListView) getActivity().findViewById(R.id.timeLogList);
        refreshDates();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void refreshDates() {
        ArrayList<Schedule> schedules = ((JobSchedulerApp)getActivity().getApplication()).getAllSchedules();
        Collections.sort(schedules,new Comparator<Schedule>() {
            @Override
            public int compare(Schedule schedule1, Schedule schedule2) {
                return schedule1.getStartTime() < schedule2.getStartTime() ? -1 : schedule1.getStartTime() == schedule1.getStartTime() ? 0 : 1;
            }
        });

        TimeLogListItemAdapter listItemAdapter = new TimeLogListItemAdapter(this.getActivity(),schedules);
        timeLogList.setAdapter(listItemAdapter);
    }
}
