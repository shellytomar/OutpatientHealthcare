package com.example.shell.outpatienthealthcare;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.shell.outpatienthealthcare.adapter.BPAdapter;
import com.example.shell.outpatienthealthcare.model.BloodPressure;
import com.example.shell.outpatienthealthcare.model.User;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodPressureActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    private RecyclerView recyclerView;
    private BPAdapter adapter;
    ArrayList<BloodPressure> bloodPressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

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
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getBloodPressureValues();
    }

    private void getBloodPressureValues(){
        Call<ArrayList<BloodPressure>> call = RestAPIClient.get().getAllBp();
        call.enqueue(new Callback<ArrayList<BloodPressure>>() {
            @Override
            public void onResponse(Call<ArrayList<BloodPressure>> call, Response<ArrayList<BloodPressure>> response) {
                bloodPressure = response.body();
                adapter = new BPAdapter(bloodPressure);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<BloodPressure>> call, Throwable t) {
                Log.i("Error ", "Cannot get Blood Pressure data");
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("id is:", "" + id);
        System.out.print(id);
        System.out.print(R.id.action_activity);
        if (id == R.id.action_logout) {
            Intent i = new Intent(BloodPressureActivity.this, SignInActivity.class);
            startActivity(i);
        } else if(id == R.id.home){
            Intent i = new Intent(BloodPressureActivity.this, DashboardActivity.class);
            startActivity(i);
        }
        else if (id == R.id.action_activity) {
            Intent i = new Intent(BloodPressureActivity.this,StepCountActivity.class);
            startActivity(i);
        }else if (id == R.id.action_bp) {
            Intent i = new Intent(BloodPressureActivity.this,BloodPressureActivity.class);
            startActivity(i);
        }else if (id == R.id.action_hr) {
            Intent i = new Intent(BloodPressureActivity.this,HeartRateActivity.class);
            startActivity(i);
        }else if (id == R.id.action_sync) {
            Intent i = new Intent(BloodPressureActivity.this,SyncActivity.class);
            startActivity(i);
        }else if (id == R.id.action_reports) {
            Intent i = new Intent(BloodPressureActivity.this, ReportActivity.class);
            startActivity(i);
        }else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
