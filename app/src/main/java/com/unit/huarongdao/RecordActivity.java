package com.unit.huarongdao;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordActivity extends Activity {

    private String TAG = "RecordActivity";
    private GameRecordDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ConstraintLayout layout = findViewById(R.id.record_btn_layout);
        layout.setBackground(getResources().getDrawable(R.drawable.record_board));
        dao = GameRecordDAO.getInstance(this);
        initContent();
    }

    private void initContent() {
        TextView recordName = findViewById(R.id.record_name);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/baqi.ttf");
        recordName.setTypeface(type);
        recordName.setTextSize(50);
        ArrayList<RecordBean> arrayList = dao.getRecord();
        LinearLayout linearLayout = findViewById(R.id.record_linear_layout);
//        linearLayout.setBackground(getResources().getDrawable(R.drawable.record_list));
        for (int i = 0; i < (arrayList.size() > 7 ? 7 : arrayList.size()); i++) {
            TextView textView = new TextView(this);
            textView.setTypeface(type);
            textView.setTextSize(25);
            textView.setPadding(120, 20, 120, -100);
            textView.setBackground(getResources().getDrawable(R.drawable.menu_button_background));
            textView.setText(arrayList.get(i).toString());
            linearLayout.addView(textView);
        }
        if (arrayList.size() < 7) {
            for (int i = 0; i < 7 - arrayList.size(); i++) {
                TextView textView = new TextView(this);
                textView.setPadding(120, 20, 120, -100);
                textView.setBackground(getResources().getDrawable(R.drawable.menu_button_background));
                linearLayout.addView(textView);
            }
        }
    }

    public void clearRecord(View view) {
        dao.clearRecord();
    }
}
