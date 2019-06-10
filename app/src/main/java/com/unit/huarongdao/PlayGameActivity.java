package com.unit.huarongdao;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class PlayGameActivity extends Activity {

    private static final String TAG = "PlayGameActivity";
    static double windowWidth, windowHeight;
    static int chessWidth, chessHeight;

    private static ArrayList<ArrayList<Integer>> chessPosList;
    private ArrayList<TouchableImageButton> chessImageList;
    private static Stack<ArrayList<Integer>> stepStack;
    private RelativeLayout chessBoard;
    private TextView timeSpent;
    private TextView stepSpent;

    private int king = -1;
    private int difficulty = -1;
    private int time = 0;
    private int step = 0;
    private boolean isStop = false;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (!isStop) {
                        updateTimeSpent();
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case 0:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            difficulty = bundle.getInt("Difficulty");
        } else {
            Toast.makeText(this, getString(R.string.system_syntax_error), Toast.LENGTH_LONG).show();
        }
        if(difficulty > 0) {
            initParams();
            initChessBoard(difficulty);
            mHandler.sendEmptyMessage(1);
        } else {
            Toast.makeText(this, getString(R.string.system_difficult_error), Toast.LENGTH_LONG).show();
        }
    }

    private void updateTimeSpent() {
        time++;
        timeSpent.setText(String.format(getString(R.string.time_check), Integer.toString(time)));
    }

    private void initChessBoard(int difficulty) {
        chessBoard.removeAllViews();
        ArrayList<Chess> chessList = (new ChessList(difficulty)).getChessList();
        chessImageList = (new ChessBoard(this, chessList, chessWidth, chessHeight)).getChessImageList();
        for (int i = 0; i < chessImageList.size(); i++) {
            if(chessList.get(i).width == 2 && chessList.get(i).height == 2){
                this.king = i;
            }
            chessBoard.addView(createImageView(this, chessImageList.get(i)));
        }
    }

    private void initParams() {
        stepStack = new Stack<ArrayList<Integer>>();
        chessPosList = new ArrayList<ArrayList<Integer>>();
        WindowManager wm = (WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;

        LinearLayout linearLayout = findViewById(R.id.linear);
        linearLayout.setBackground(getResources().getDrawable(R.drawable.game_board));

        LinearLayout topText = findViewById(R.id.top_text);
        LinearLayout.LayoutParams topTextParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.8), (int) (windowHeight * 0.05));
        topTextParams.setMargins((int) (windowWidth * 0.1), (int) (windowHeight * 0.05), (int) (windowWidth * 0.1), (int) (windowHeight * 0.02));
        topText.setLayoutParams(topTextParams);

        timeSpent = findViewById(R.id.timetext);
        stepSpent = findViewById(R.id.steptext);
        LinearLayout.LayoutParams timeTextParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.3), (int) (windowHeight * 0.05));
        timeTextParams.setMargins((int) (windowWidth * 0.01), 0, 0, 0);
        LinearLayout.LayoutParams stepTextParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.3), (int) (windowHeight * 0.05));
        stepTextParams.setMargins((int) (windowWidth * 0.19), 0, 0, 0);
        timeSpent.setLayoutParams(timeTextParams);
        stepSpent.setLayoutParams(stepTextParams);

        chessBoard = findViewById(R.id.chessboard);
        chessBoard.setBackground(getResources().getDrawable(R.drawable.game_board_middle));
        LinearLayout.LayoutParams chessBoardParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.8), (int) (windowHeight * 0.6));
        chessBoardParams.setMargins((int) (windowWidth * 0.1), (int) (windowHeight * 0.05), (int) (windowWidth * 0.1), 0);
        chessBoard.setLayoutParams(chessBoardParams);
        chessWidth = (int) (windowWidth * 0.8 / 4);
        chessHeight = (int) (windowHeight * 0.6 / 5);

        LinearLayout downMenu = findViewById(R.id.down_menu);
        LinearLayout.LayoutParams downMenuParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.8), (int) (windowHeight * 0.05));
        downMenuParams.setMargins((int) (windowWidth * 0.11), 0, (int) (windowWidth * 0.1),  (int) (windowHeight * 0.05));
        downMenu.setLayoutParams(downMenuParams);

        Button quitBtn = findViewById(R.id.quitbtn);
        LinearLayout.LayoutParams quitLayoutParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.2), (int) (windowHeight * 0.05));
        quitBtn.setLayoutParams(quitLayoutParams);

        Button resetBtn = findViewById(R.id.resetbtn);
        LinearLayout.LayoutParams resetLayoutParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.2), (int) (windowHeight * 0.05));
        resetLayoutParams.setMargins((int) (windowWidth * 0.03), 0, 0, 0);
        resetBtn.setLayoutParams(resetLayoutParams);

        Button backBtn = findViewById(R.id.backbtn);
        LinearLayout.LayoutParams backLayoutParams = new LinearLayout.LayoutParams((int) (windowWidth * 0.2), (int) (windowHeight * 0.05));
        backLayoutParams.setMargins((int) (windowWidth * 0.05), 0, (int) (windowWidth * 0.1), 0);
        backBtn.setLayoutParams(backLayoutParams);
    }

    private ArrayList<Integer> getChessPosInfo(ArrayList<Integer> chessPos, Chess chess, int id) {
        chessPos.add(id);
        chessPos.add(chess.pos_x);
        chessPos.add(chess.pos_y);
        chessPos.add(chess.pos_x + chess.width);
        chessPos.add(chess.pos_y + chess.height);
        return chessPos;
    }

    public void reInit(View view) {
        stepStack = new Stack<ArrayList<Integer>>();
        step = -1;
        addStep();
        time = 0;
        for (int i = 0; i < 10; i++) {
            int id = chessImageList.get(i).getId();
            TouchableImageButton touchableImageButton = (TouchableImageButton) findViewById(id);
            Chess chess = chessImageList.get(i).getChess();
            touchableImageButton.layout((int) (chess.pos_x * chessWidth), (int) (chess.pos_y * chessHeight), (int) ((chess.pos_x + chess.width) * chessWidth), (int) ((chess.pos_y + chess.height) * chessHeight));
            ArrayList<Integer> chessPos = new ArrayList<Integer>();
            chessPosList.set(i, getChessPosInfo(chessPos, chess, id));
        }
    }

    public TouchableImageButton createImageView(Context context, TouchableImageButton image) {
        Chess chess = image.getChess();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(((int) (chess.width * chessWidth)),
                ((int) (chess.height * chessHeight)));
        params.setMargins(((int) (chess.pos_x * chessWidth)), ((int) (chess.pos_y * chessHeight)), 0, 0);
        image.setLayoutParams(params);
        ArrayList<Integer> chessPos = new ArrayList<Integer>();
        chessPosList.add(getChessPosInfo(chessPos, chess, image.getId()));
        return image;
    }

    public void renewPosLR(int chessIndex, int left, int right) {
        ArrayList<Integer> stepList = new ArrayList<Integer>();
        ArrayList<Integer> posList = chessPosList.get(chessIndex);
        stepList.add(chessIndex);
        stepList.add(posList.get(1));
        stepList.add(posList.get(2));
        stepList.add(posList.get(3));
        stepList.add(posList.get(4));
        stepList.add(left);
        stepList.add(posList.get(2));
        stepList.add(right);
        stepList.add(posList.get(4));
        stepStack.add(stepList);
        this.addStep();
        chessPosList.get(chessIndex).set(1, left);
        chessPosList.get(chessIndex).set(3, right);
    }

    public void renewPosTD(int chessIndex, int top, int down) {
        ArrayList<Integer> stepList = new ArrayList<Integer>();
        ArrayList<Integer> posList = chessPosList.get(chessIndex);
        stepList.add(chessIndex);
        stepList.add(posList.get(1));
        stepList.add(posList.get(2));
        stepList.add(posList.get(3));
        stepList.add(posList.get(4));
        stepList.add(posList.get(1));
        stepList.add(top);
        stepList.add(posList.get(3));
        stepList.add(down);
        stepStack.add(stepList);
        addStep();
        chessPosList.get(chessIndex).set(2, top);
        chessPosList.get(chessIndex).set(4, down);
    }

    public ArrayList<Integer> verifyMovement(int left, int top, int right, int down, int id, int direction, int offsetX, int offsetY, boolean flag) {
        if (Math.abs(offsetX) > chessWidth) {
            left -= offsetX;
            right -= offsetX;
            offsetX = chessWidth * offsetX / Math.abs(offsetX);
            left += offsetX;
            right += offsetX;
        }
        if (Math.abs(offsetY) > chessHeight) {
            top -= offsetY;
            down -= offsetY;
            offsetY = chessHeight * offsetY / Math.abs(offsetY);
            top += offsetY;
            down += offsetY;
        }
        if (left < -1 || top < -1 || right > 4 * chessWidth || down > 5 * chessHeight) {
            return null;
        }
        int idIndex = -1;
        if (direction == 0) {
            if (offsetX > 0) {
                left = left % chessWidth == 0 ? left / chessWidth : left / chessWidth + 1;
                right = right % chessWidth == 0 ? right / chessWidth : right / chessWidth + 1;
                top = top / chessHeight;
                down = down / chessHeight;
                for (int cnt = 0; cnt < 10; cnt++) {
                    ArrayList<Integer> oChess = chessPosList.get(cnt);
                    if (oChess.get(0) == id) {
                        idIndex = cnt;
                    }
                    if (oChess.get(0) != id && !(oChess.get(2) >= down || oChess.get(4) <= top) && left <= oChess.get(1) && right > oChess.get(1)) {
                        return null;
                    }
                }
                if (chessPosList.get(idIndex).get(1) != left && flag) {
                    renewPosLR(idIndex, left, right);
                }
            } else {
                left = left / chessWidth;
                right = right / chessWidth;
                top = top / chessHeight;
                down = down / chessHeight;
                for (int cnt = 0; cnt < 10; cnt++) {
                    ArrayList<Integer> oChess = chessPosList.get(cnt);
                    if (oChess.get(0) == id) {
                        idIndex = cnt;
                    }
                    if (oChess.get(0) != id && !(oChess.get(2) >= down || oChess.get(4) <= top) && left < oChess.get(3) && right >= oChess.get(3)) {
                        return null;
                    }
                }
                if (chessPosList.get(idIndex).get(1) != left && flag) {
                    renewPosLR(idIndex, left, right);
                }
            }
        } else {
            if (offsetY > 0) {
                top = top % chessHeight == 0 ? top / chessHeight : top / chessHeight + 1;
                down = down % chessHeight == 0 ? down / chessHeight : down / chessHeight + 1;
                left = left / chessWidth;
                right = right / chessWidth;
                for (int cnt = 0; cnt < 10; cnt++) {
                    ArrayList<Integer> oChess = chessPosList.get(cnt);
                    if (oChess.get(0) == id) {
                        idIndex = cnt;
                    }
                    if (oChess.get(0) != id && !(oChess.get(1) >= right || oChess.get(3) <= left) && top <= oChess.get(2) && down > oChess.get(2)) {
                        return null;
                    }
                }
                if (chessPosList.get(idIndex).get(2) != top && flag) {
                    renewPosTD(idIndex, top, down);
                }
            } else {
                top = top / chessHeight;
                down = down / chessHeight;
                left = left / chessWidth;
                right = right / chessWidth;
                for (int cnt = 0; cnt < 10; cnt++) {
                    ArrayList<Integer> oChess = chessPosList.get(cnt);
                    if (oChess.get(0) == id) {
                        idIndex = cnt;
                    }
                    if (oChess.get(0) != id && !(oChess.get(1) >= right || oChess.get(3) <= left) && top < oChess.get(4) && down >= oChess.get(4)) {
                        return null;
                    }
                }
                if (chessPosList.get(idIndex).get(2) != top && flag) {
                    renewPosTD(idIndex, top, down);
                }
            }
        }
        checkVictory();
        return chessPosList.get(idIndex);
    }

    private void checkVictory() {
        ArrayList<Integer> kingPos = chessPosList.get(this.king);
        Log.d(TAG,kingPos.get(1) + " " +  kingPos.get(2));
        if (kingPos.get(1) == 1 && kingPos.get(2) == 3) {
            this.isStop = true;
            final EditText editText = new EditText(this);
            AlertDialog.Builder inputDialog =
                    new AlertDialog.Builder(this);
            inputDialog.setTitle(R.string.victory_text).setView(editText);
            inputDialog.setNegativeButton("返回主菜单",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handleDatabase(editText.getText().toString());
                            finish();
                        }
                    });
            inputDialog.setPositiveButton("下一关",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handleDatabase(editText.getText().toString());
                            difficulty++;
                            initParams();
                            initChessBoard(difficulty);
                            reInit(null);
                        }
                    }).show();
        }
    }

    private void handleDatabase(String name) {
        Log.d(TAG, name);
        GameRecordDAO dao = GameRecordDAO.getInstance(this);
        dao.addRecord(difficulty, time, step, name);
    }

    public void addStep() {
        step++;
        stepSpent.setText(String.format(getString(R.string.step_check), Integer.toString(step)));
    }

    public void backStep(View view) {
        if (stepStack.empty()) {
            Toast.makeText(PlayGameActivity.this, getString(R.string.not_move_error), Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Integer> backStep = stepStack.pop();
        int chessIndex = backStep.get(0);
        TouchableImageButton chess = chessImageList.get(chessIndex);
        ArrayList<Integer> nowStep = chessPosList.get(chessIndex);
        if (!backStep.get(5).equals(nowStep.get(1)) || !backStep.get(6).equals(nowStep.get(2)) || !backStep.get(7).equals(nowStep.get(3)) || !backStep.get(8).equals(nowStep.get(4))) {
            Toast.makeText(PlayGameActivity.this, R.string.system_move_error, Toast.LENGTH_SHORT).show();
        }
        chess.layout(backStep.get(1) * chessWidth, backStep.get(2) * chessHeight, backStep.get(3) * chessWidth, backStep.get(4) * chessHeight);
        chessPosList.get(chessIndex).set(1, backStep.get(1));
        chessPosList.get(chessIndex).set(2, backStep.get(2));
        chessPosList.get(chessIndex).set(3, backStep.get(3));
        chessPosList.get(chessIndex).set(4, backStep.get(4));
        addStep();
    }

    public void exitGame(View view) {
        finish();
    }
}
