package com.unit.huarongdao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GameRecordDAO {
    private GameRecordDbHelper dbHelper;
    private String tableName = "game_record";
    private static GameRecordDAO gameRecordDAO;

    private GameRecordDAO(Context context) {
        dbHelper = new GameRecordDbHelper(context, "game_record.db", null, 1);
    }

    public static synchronized GameRecordDAO getInstance(Context ctx){
        if(gameRecordDAO == null){
            gameRecordDAO = new GameRecordDAO(ctx);
        }
        return  gameRecordDAO;
    }

    public void addRecord(int difficulty, int time, int step, String record_name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("difficulty", difficulty);
        values.put("time", time);
        values.put("step", step);
        values.put("record_name", record_name);
        db.insert(tableName, null, values);
    }

    public ArrayList<RecordBean> getRecord() {
        ArrayList<RecordBean> recordBeans = new ArrayList<RecordBean>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from game_record order by _id;", null);
        while(cursor.moveToNext()) {
            int difficulty = cursor.getInt(cursor.getColumnIndex("difficulty"));
            int time = cursor.getInt(cursor.getColumnIndex("time"));
            int step = cursor.getInt(cursor.getColumnIndex("step"));
            String record_name = cursor.getString(cursor.getColumnIndex("record_name"));
            RecordBean bean = new RecordBean(difficulty, time, step, record_name);
            recordBeans.add(bean);
        }
        cursor.close();
        return recordBeans;
    }

    public void clearRecord() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableName);
    }
}
