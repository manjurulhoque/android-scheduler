package com.manjurulhoque.scheduler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.manjurulhoque.scheduler.R;
import com.manjurulhoque.scheduler.dbhelper.EmailDatabaseHelper;
import com.manjurulhoque.scheduler.model.Email;

import java.util.List;

public class EmailArrayAdapter extends BaseAdapter {

    private Context context;
    private List<Email> emails;
    EmailDatabaseHelper databaseHelper;
    private LayoutInflater layoutInflater;

    public EmailArrayAdapter(@NonNull Context context, List<Email> emails) {

        this.context = context;
        this.emails = emails;

        databaseHelper = new EmailDatabaseHelper(context);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return emails.size();
    }

    @Override
    public Object getItem(int i) {
        return emails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_row_email, null);
            holder = new ViewHolder();

            holder.textViewRecipient = convertView.findViewById(R.id.textViewRecipient);
            holder.textViewScheduledTime = convertView.findViewById(R.id.textViewScheduledTime);
            holder.textViewSubject = convertView.findViewById(R.id.textViewSubject);
            holder.textViewScheduledDate = convertView.findViewById(R.id.textViewScheduledDate);
            holder.textViewBody = convertView.findViewById(R.id.textViewBody);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewRecipient.setText(emails.get(position).getRecipient());
        holder.textViewScheduledTime.setText(emails.get(position).getTime());
        holder.textViewSubject.setText(emails.get(position).getSubject());
        holder.textViewScheduledDate.setText(emails.get(position).getDate());
        holder.textViewBody.setText(emails.get(position).getBody());

        return convertView;
    }

    class ViewHolder {
        TextView textViewRecipient;
        TextView textViewScheduledTime;
        TextView textViewSubject;
        TextView textViewScheduledDate;
        TextView textViewBody;
    }
}
