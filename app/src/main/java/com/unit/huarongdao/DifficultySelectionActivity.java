package com.unit.huarongdao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DifficultySelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);
        initParams();
    }

    private void initParams() {
        ConstraintLayout layout = findViewById(R.id.constraint);
        layout.setBackground(getResources().getDrawable(R.drawable.dificulty_selection_backgroundground));
        TextView textView = findViewById(R.id.difficulty_name);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/baqi.ttf");
        textView.setTypeface(type);
        textView.setTextSize(35);
    }

    public void chooseDifficulty(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        int difficulty = 0;
        switch (view.getId()) {
            case R.id.button1:
                difficulty = 1;
                break;
            case R.id.button2:
                difficulty = 2;
                break;
            case R.id.button3:
                difficulty = 3;
                break;
            case R.id.button4:
                difficulty = 4;
                break;
            case R.id.button5:
                difficulty = 5;
                break;
            case R.id.button6:
                difficulty = 6;
                break;
        }
        intent.putExtra("Difficulty", difficulty);
        startActivity(intent);
    }
}
