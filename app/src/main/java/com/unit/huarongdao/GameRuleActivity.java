package com.unit.huarongdao;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

public class GameRuleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rule);
        initParams();
    }

    private void initParams() {
        ConstraintLayout constraintLayout = findViewById(R.id.game_rule_constraint);
        constraintLayout.setBackground(getResources().getDrawable(R.drawable.game_rule_background));
    }
}
