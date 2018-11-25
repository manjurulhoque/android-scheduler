package com.manjurulhoque.scheduler.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.manjurulhoque.scheduler.R;
import com.manjurulhoque.scheduler.activity.email.EmailSchedulerActivity;
import com.manjurulhoque.scheduler.activity.sms.SmsSchedulerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cardSmsScheduler)
    public CardView cardSmsScheduler;
    @BindView(R.id.cardEmailScheduler)
    public CardView cardEmailScheduler;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        cardSmsScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SmsSchedulerActivity.class));
            }
        });

        cardEmailScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EmailSchedulerActivity.class));
            }
        });

        MainActivity.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
