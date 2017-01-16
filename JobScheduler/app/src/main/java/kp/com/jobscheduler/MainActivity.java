package kp.com.jobscheduler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import kp.com.jobscheduler.data.Database;
import kp.com.jobscheduler.data.PayCycle;
import kp.com.jobscheduler.data.Schedule;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Database database;
    FrameLayout mainFrame;
    LayoutInflater inflater;
    ScheduleListFragment scheduleListFragment;
    PayCycleFragment payCycleFragment;
    Fragment currentOpenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = LayoutInflater.from(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddNewSchedule.class);
                startActivityForResult(i,1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((JobSchedulerApp)getApplication()).setDatabase(new Database(this));
        database = ((JobSchedulerApp)getApplication()).getDatabase();

        initFragments();
        //showFragment(R.id.scheduleListFragment);
        currentOpenFragment = scheduleListFragment;
        navigationView.setCheckedItem(R.id.viewShifts);
    }

    private void initFragments() {
        scheduleListFragment = (ScheduleListFragment) getSupportFragmentManager().findFragmentById(R.id.scheduleListFragment);
        payCycleFragment = (PayCycleFragment) getSupportFragmentManager().findFragmentById(R.id.payCycleFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        scheduleListFragment.refreshDates();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void refreshDates() {
        this.scheduleListFragment.refreshDates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_calculate_pay:
                startActivity(new Intent(this,CalculatePay.class));
                break;
            case R.id.action_set_work_location:
                startActivity(new Intent(this,MapsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        showFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showFragment(int id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (currentOpenFragment != null) {
            fragmentTransaction.hide(currentOpenFragment);
        }
        switch (id) {
            case R.id.viewShifts:
                fragmentTransaction.show(scheduleListFragment);
                currentOpenFragment = scheduleListFragment;
                break;
            case R.id.viewPayCycles:
                fragmentTransaction.show(payCycleFragment);
                currentOpenFragment = payCycleFragment;
                break;
            case R.id.backupData:
                break;
            case R.id.notifications:
                break;
            case R.id.settings:
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

}
