package kp.com.jobscheduler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import kp.com.jobscheduler.data.Schedule;

/**
 * Created by Kartik on 04-Jan-17.
 */

public class TimeLogListItemAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    Activity activity;
    ArrayList<Schedule> schedules;

    public TimeLogListItemAdapter(Activity activity, ArrayList<Schedule> schedules) {
        this.activity = activity;
        this.schedules = schedules;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TimeLogListItem vi = (TimeLogListItem) convertView;
        if(vi == null) {
            vi = (TimeLogListItem)TimeLogListItem.inflate(parent,activity);
        }
        Schedule schedule = schedules.get(position);
        vi.setSchedule(schedule);

        return vi;
    }
}
