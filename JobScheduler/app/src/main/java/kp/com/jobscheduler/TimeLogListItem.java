package kp.com.jobscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import kp.com.jobscheduler.data.Schedule;

/**
 * Created by Kartik on 05-Jan-17.
 */

public class TimeLogListItem extends LinearLayout implements View.OnClickListener {

    private Schedule schedule;
    TextView timeLogTextView;
    Button edit,delete;
    Activity parentActivity;

    public TimeLogListItem(Context context) {
        this(context,null);
    }

    public TimeLogListItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeLogListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.timelog_list_item_children,this,true);
        setupChildren();
    }

    private void setupChildren() {
        timeLogTextView = (TextView) findViewById(R.id.sheduleListItemText);

        edit = (Button) findViewById(R.id.editSchedule);
        edit.setOnClickListener(this);
        edit.setTag("edit");

        delete = (Button) findViewById(R.id.deleteSchedule);
        delete.setOnClickListener(this);
        delete.setTag("delete");
    }

    public static TimeLogListItem inflate(ViewGroup parent, Activity parentActivity) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.timelog_list_item, parent, false);
        TimeLogListItem logListItem = (TimeLogListItem) layout;
        logListItem.setParentActivity(parentActivity);
        return logListItem;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        timeLogTextView.setText(schedule.getStartTimeFormatted() + "  -  " + schedule.getEndTimeFormatted());
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch (tag) {
            case "edit":
            Log.d("Id = ", v.getTag() + "");
            Intent i = new Intent(this.getContext(), AddNewSchedule.class);
            i.putExtra("schedule", schedule);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.parentActivity.startActivityForResult(i, 1);
            break;
            case "delete":
                ((JobSchedulerApp)parentActivity.getApplication()).deleteSchedule(schedule.getId());
                ((MainActivity)parentActivity).refreshDates();
        }
    }

    public void setParentActivity(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }
}
