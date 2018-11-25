package com.manjurulhoque.scheduler.activity.email;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manjurulhoque.scheduler.MyBroadcastReceiver;
import com.manjurulhoque.scheduler.R;
import com.manjurulhoque.scheduler.dbhelper.EmailDatabaseHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEmailScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.textViewTime)
    TextView textViewTime;
    @BindView(R.id.textViewDate)
    TextView textViewDate;
    @BindView(R.id.editTextRecipient)
    EditText editTextRecipient;
    @BindView(R.id.editTextSubject)
    EditText editTextSubject;
    @BindView(R.id.editTextBody)
    EditText editTextBody;


    int mHour, mMinute, mYear, mMonth, mDay;
    Calendar calendar;
    EmailDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_email_schedule);
        ButterKnife.bind(this);
        databaseHelper = new EmailDatabaseHelper(this);
        calendar = Calendar.getInstance();
    }

    @OnClick(R.id.relativeLayoutSelectDate)
    public void getDate() {
        // initialize
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, mYear, mMonth, mDay);
        datePickerDialog.show(getFragmentManager(), "datePickerDialog");
    }

    @OnClick(R.id.relativeLayoutSelectTime)
    public void getTime() {
        // initialize
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, mHour, mMinute, DateFormat.is24HourFormat(this));
        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        calendar.add(Calendar.DATE, 0);

        final String[] MONTHS = {"Jan", "Feb", "Mar",
                "Apr", "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};
        String mon = MONTHS[monthOfYear];

        String selectedDate = dayOfMonth + "  " + mon
                + " " + year;

        SpannableString ss1 = new SpannableString(selectedDate);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0, 2, 0); // set
        textViewDate.setText(ss1);

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mHour = hourOfDay;
        mMinute = minute;

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hourOfDay);

        calendar.set(Calendar.AM_PM, Calendar.AM);

        String str;
        if (calendar.get(Calendar.AM_PM) == Calendar.AM)
            str = "AM";
        else
            str = "PM";

        String hours;
        if (calendar.get(Calendar.HOUR) > 9) {
            hours = String.valueOf(calendar.get(Calendar.HOUR));
        } else {
            hours = "0" + String.valueOf(calendar.get(Calendar.HOUR));
        }

        String minutes;
        if (minute > 9) {
            minutes = String.valueOf(minute);
        } else {
            minutes = "0" + String.valueOf(minute);
        }
        if (hours.equalsIgnoreCase("00")) {
            hours = "12";
        }

        String selectedTime = hours + ":" + minutes
                + "  " + str;

        SpannableString ss2 = new SpannableString(selectedTime);
        ss2.setSpan(new RelativeSizeSpan(1.5f), 0, 5, 0);

        textViewTime.setText(ss2);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hourOfDay);
    }

    @OnClick(R.id.btnSetSchedule)
    public void setSchedule() {
        String recipient = editTextRecipient.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String body = editTextBody.getText().toString().trim();

        if (!TextUtils.isEmpty(recipient) && !TextUtils.isEmpty(subject) && !TextUtils.isEmpty(body)) {
            calendar.set(mYear, mMonth, mDay, mHour, mMinute);
            int _id = (int) System.currentTimeMillis();
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            String strAction = "com.scheduler.action.EMAIL_NOTIFICATION";
            intent.putExtra("id", _id);
            intent.setAction(strAction);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, intent, 0);

            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            if (databaseHelper.addEmail(_id, recipient, subject, body, textViewTime.getText().toString(),
                    textViewDate.getText().toString(), (int) calendar.getTimeInMillis())) {
                Toast.makeText(this, "Scheduled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
            this.onBackPressed();
        } else {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
