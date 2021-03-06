package com.example.shell.outpatienthealthcare;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class ReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClickBPReport(View view){
        Intent intent = new Intent(ReportActivity.this, BPReportActivity.class);
        startActivity(intent);
    }

    public void onClickActivityReport(View view){
        Intent intent = new Intent(ReportActivity.this, DailyReportActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("id is:", "" + id);
        System.out.print(id);
        System.out.print(R.id.action_activity);
        if (id == R.id.action_logout) {
            Intent i = new Intent(ReportActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.home) {
            Intent i = new Intent(ReportActivity.this, DashboardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(ReportActivity.this, StepCountActivity.class);
            startActivity(i);
        } else if (id == R.id.action_bp) {
            Intent i = new Intent(ReportActivity.this, BloodPressureActivity.class);
            startActivity(i);
        } else if (id == R.id.action_hr) {
            Intent i = new Intent(ReportActivity.this, HeartRateActivity.class);
            startActivity(i);
        }else if (id == R.id.action_sync) {
            Intent i = new Intent(ReportActivity.this,SyncActivity.class);
            startActivity(i);
        } else if (id == R.id.action_reports) {
            Intent i = new Intent(ReportActivity.this, ReportActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
