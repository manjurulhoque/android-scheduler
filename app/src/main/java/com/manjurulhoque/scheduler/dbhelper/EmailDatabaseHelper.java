package com.manjurulhoque.scheduler.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.manjurulhoque.scheduler.model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailDatabaseHelper extends SQLiteOpenHelper {
    private static final String EMAIL_TABLE_NAME = "email_schedules";

    public EmailDatabaseHelper(Context context) {
        super(context, EMAIL_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + EMAIL_TABLE_NAME + "(_ID INTEGER PRIMARY KEY,"
                + "recipient VARCHAR,"
                + "subject VARCHAR,"
                + "body VARCHAR,"
                + "time VARCHAR,"
                + "date VARCHAR,"
                + "mail_milli INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + EMAIL_TABLE_NAME);
        onCreate(db);
    }

    public boolean addEmail(int id, String recipient, String subject, String body, String time, String date, int mail_milli) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_ID", id);
        contentValues.put("recipient", recipient);
        contentValues.put("subject", subject);
        contentValues.put("body", body);
        contentValues.put("time", time);
        contentValues.put("date", date);
        contentValues.put("mail_milli", mail_milli);

        long result = db.insert(EMAIL_TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public List<Email> getAll() {
        List<Email> emails = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EMAIL_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String id = String.valueOf(cursor.getInt(0));
            String recipient = cursor.getString(1);
            String subject = cursor.getString(2);
            String body = cursor.getString(3);
            String time = cursor.getString(4);
            String date = cursor.getString(5);
            int mail_milli = cursor.getInt(6);
            Email email = new Email(id, recipient, subject, body, time, date, mail_milli);
            emails.add(email);
        }

        cursor.close();

        return emails;
    }

    public boolean deleteById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(EMAIL_TABLE_NAME, "_ID=?", new String[]{id}) > 0;
    }
}
