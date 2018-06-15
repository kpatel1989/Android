package kp.com.database;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import com.google.tabmanager.TabManager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    KpTaskDb kpDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        TabManager tabManager = new TabManager(this, tabHost, R.id.realtabcontent);

        kpDb = new KpTaskDb(getApplicationContext());
        // add a tab for each list in the database
        ArrayList<KpList> lists = kpDb.getLists();
        if (lists != null && lists.size() > 0) {
            for (KpList list : lists) {
                TabHost.TabSpec tabSpec = tabHost.newTabSpec(list.getName());
                tabSpec.setIndicator(list.getName());
                tabManager.addTab(tabSpec, TaskListFragment.class, null);
            }
        }
    }
}
