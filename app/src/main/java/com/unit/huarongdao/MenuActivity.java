package com.unit.huarongdao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ConstraintLayout layout = findViewById(R.id.constraint);
        layout.setBackground(getResources().getDrawable(R.drawable.chess_board));
        TextView textViewUp = findViewById(R.id.game_name_up);
        TextView textViewDown = findViewById(R.id.game_name_down);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/baqi.ttf");
        textViewUp.setTypeface(type);
        textViewUp.setTextSize(50);
        textViewDown.setTypeface(type);
        textViewDown.setTextSize(40);
        Button startGame = findViewById(R.id.start_game);
        startGame.setTypeface(type);
        startGame.setTextSize(25);
        Button gameRule = findViewById(R.id.game_rule);
        gameRule.setTypeface(type);
        gameRule.setTextSize(25);
        Button rankList = findViewById(R.id.rank_list);
        rankList.setTypeface(type);
        rankList.setTextSize(25);
        Button exitGame = findViewById(R.id.exit_game);
        exitGame.setTypeface(type);
        exitGame.setTextSize(25);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, DifficultySelectionActivity.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
        finish();
    }

    public void rankList(View view) {
        Intent intent = new Intent(MenuActivity.this, RecordActivity.class);
        startActivity(intent);
    }

    public void gameRule(View view) {
        Intent intent = new Intent(MenuActivity.this, GameRuleActivity.class);
        startActivity(intent);
    }
}
