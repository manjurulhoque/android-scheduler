package com.manjurulhoque.scheduler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manjurulhoque.scheduler.R;
import com.manjurulhoque.scheduler.dbhelper.SmsDatabaseHelper;
import com.manjurulhoque.scheduler.model.Sms;

import java.util.ArrayList;
import java.util.List;

public class SmsArrayAdapter extends BaseAdapter {

    private Context context;
    private List<Sms> smsArrayList;
    SmsDatabaseHelper databaseHelper;
    private LayoutInflater layoutInflater;

    public SmsArrayAdapter(@NonNull Context context, List<Sms> smsList) {

        this.context = context;
        this.smsArrayList = smsList;

        databaseHelper = new SmsDatabaseHelper(context);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return smsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return smsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_row_sms, null);
            holder = new ViewHolder();

            holder.textViewNumber = convertView.findViewById(R.id.textViewNumber);
            holder.textViewTime = convertView.findViewById(R.id.textViewTime);
            holder.textViewMessage = convertView.findViewById(R.id.textViewMessage);
            holder.textViewDate = convertView.findViewById(R.id.textViewDate);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNumber.setText(smsArrayList.get(position).getNumber());
        holder.textViewMessage.setText(smsArrayList.get(position).getMessage());
        holder.textViewTime.setText(smsArrayList.get(position).getTime());
        holder.textViewDate.setText(smsArrayList.get(position).getDate());

        return convertView;
    }

    class ViewHolder {
        TextView textViewNumber;
        TextView textViewTime;
        TextView textViewMessage;
        TextView textViewDate;
    }
}
