package com.manjurulhoque.scheduler.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.manjurulhoque.scheduler.R;
import com.manjurulhoque.scheduler.adapter.SmsArrayAdapter;
import com.manjurulhoque.scheduler.dbhelper.SmsDatabaseHelper;
import com.manjurulhoque.scheduler.model.Sms;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsSchedulerActivity extends AppCompatActivity {

    @BindView(R.id.smsListView)
    public ListView smsListView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.textViewNoSchedule)
    TextView textViewNoSchedule;

    private List<Sms> smsArrayList = new ArrayList<Sms>();
    private SmsArrayAdapter smsArrayAdapter;
    SmsDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_scheduler);
        ButterKnife.bind(this);

        databaseHelper = new SmsDatabaseHelper(this);

        fetchSms();
    }

    private void fetchSms() {
        smsArrayList = databaseHelper.getAll();
        if (smsArrayList.size() > 0) {
            smsListView.setVisibility(View.VISIBLE);
            textViewNoSchedule.setVisibility(View.GONE);
            Log.d("SMSARRAY", smsArrayList.get(0).toString());
        }
        smsArrayAdapter = new SmsArrayAdapter(this, smsArrayList);
        smsListView.setAdapter(smsArrayAdapter);
    }

    @OnClick(R.id.fab)
    public void fabClick() {
        startActivity(new Intent(this, CreateSmsScheduleActivity.class));
    }
}
