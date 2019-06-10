package com.unit.huarongdao;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class ChessBoard {

    private static final String TAG = "ChessBoard";
    PlayGameActivity context;
    private ArrayList<Chess> chessList;
    private ArrayList<TouchableImageButton> chessImageList;
    private View.OnTouchListener listener;
    private int chessWidth;
    private int chessHeight;

    ChessBoard(Context context, ArrayList<Chess> chessList, double chessWidth, double chessHeight) {
        this.context = (PlayGameActivity) context;
        this.chessList = chessList;
        this.chessImageList = new ArrayList<TouchableImageButton>(10);
        this.chessWidth = (int) chessWidth;
        this.chessHeight = (int) chessHeight;
        initListener();
        initChessImageList();
    }

    private void initListener() {
        listener = new View.OnTouchListener() {
            private int lastX;
            private int lastY;
            private int lastImageX;
            private int lastImageY;
            private int direction = -1;

            public void actionMovement(View v, int x, int y) {
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                if (direction == -1) {
                    direction = Math.abs(offsetX) > Math.abs(offsetY) ? 0 : 1;
                }
                if (direction == 0) {
                    offsetY = 0;
                } else {
                    offsetX = 0;
                }
                if(context.verifyMovement(v.getLeft() + offsetX, v.getTop() + offsetY, v.getRight() + offsetX, v.getBottom() + offsetY, v.getId(), direction, offsetX, offsetY,false) != null) {
                    v.layout(v.getLeft() + offsetX, v.getTop() + offsetY, v.getRight() + offsetX, v.getBottom() + offsetY);
                }
            }

            public void actionUpMovement(View v) {
                int chessUniWidth = v.getRight() - v.getLeft();
                int chessUniHeight = v.getBottom() - v.getTop();
                int offsetX = v.getLeft() - lastImageX;
                int offsetY = v.getTop() - lastImageY;
                if (direction == 0) {
                    if (offsetX > 0) {
                        offsetX = (offsetX % chessWidth) > (chessWidth / 3) ? (offsetX / chessWidth + 1) * chessWidth : (offsetX / chessWidth) * chessWidth;
                    } else {
                        offsetX = -offsetX;
                        offsetX = (offsetX % chessWidth) > (chessWidth / 3) ? -(offsetX / chessWidth + 1) * chessWidth : -(offsetX / chessWidth) * chessWidth;
                    }
                    offsetY = 0;
                } else {
                    if (offsetY > 0) {
                        offsetY = (offsetY % chessHeight) > (chessHeight / 3) ? (offsetY / chessHeight + 1) * chessHeight : (offsetY / chessHeight) * chessHeight;
                    } else {
                        offsetY = -offsetY;
                        offsetY = (offsetY % chessHeight) > (chessHeight / 3) ? -(offsetY / chessHeight + 1) * chessHeight : -(offsetY / chessHeight) * chessHeight;
                    }
                    offsetX = 0;
                }
                ArrayList<Integer> returnList = context.verifyMovement(lastImageX + offsetX, lastImageY + offsetY, lastImageX + chessUniWidth + offsetX, lastImageY + chessUniHeight + offsetY, v.getId(), direction, offsetX, offsetY, true);
                if(returnList != null) {
                    v.layout(returnList.get(1) * chessWidth, returnList.get(2) * chessHeight, returnList.get(3) * chessWidth, returnList.get(4) * chessHeight);
                }
                direction = -1;
            }

            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        lastImageX = v.getLeft();
                        lastImageY = v.getTop();
                        lastX = x;
                        lastY = y;
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        actionMovement(v, x, y);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        actionUpMovement(v);
                        break;
                    }
                }
                return false;
            }
        };
    }

    private void initChessImageList() {
        for (int i = 0; i < chessList.size(); i++) {
            TouchableImageButton imageView = createImageView(context, chessList.get(i));
            imageView.setOnTouchListener(listener);
            chessImageList.add(imageView);
        }
    }

    public TouchableImageButton createImageView(Context context, Chess chess) {
        TouchableImageButton img = new TouchableImageButton(context);
        img.setChess(chess);
        img.setVisibility(View.VISIBLE);
        img.setId(View.generateViewId());
        switch(chess.type) {
            case 1:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_soldier));
                img.textView.setTextSize(30);
                img.textView.setPaddingRelative(0,60,0, 0);
                img.textView.setText("卒");
                break;
            case 2:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_machao));
                img.textView.setPaddingRelative(0,60,0, 0);
                img.textView.setText("马\n\n超");
                break;
            case 3:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_huangzhong));
                img.textView.setText("黄\n\n忠");
                break;
            case 4:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_zhaoyun));
                img.textView.setText("赵\n\n云");
                break;
            case 5:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_zhangfei));
                img.textView.setText("张\n\n飞");
                break;
            case 6:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_guanyu));
                img.textView.setTextSize(35);
                img.textView.setText("关 羽");
                break;
            case 7:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_caocao));
                img.textView.setTextSize(60);
                img.textView.setPaddingRelative(0,120,0, 0);
                img.textView.setText("曹操");
                break;
            case 8:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_guanyu));
                img.textView.setTextSize(35);
                img.textView.setText("张 飞");
                break;
            case 9:
                img.setBackground(context.getResources().getDrawable(R.drawable.chess_background_guanyu));
                img.textView.setTextSize(35);
                img.textView.setText("赵 云");
                break;
        }
        return img;
    }

    public ArrayList<TouchableImageButton> getChessImageList() {
        return chessImageList;
    }
}
