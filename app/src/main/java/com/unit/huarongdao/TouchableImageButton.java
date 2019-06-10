package com.unit.huarongdao;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchableImageButton extends LinearLayout {
    public ImageView imageView;
    public TextView textView;
    private int lastX;
    private int lastY;
    private Chess chess;

    public TouchableImageButton(Context context) {
        super(context);
        imageView = new ImageView(context);
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        textView.setTextSize(30);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/lishu.ttf");
        textView.setTypeface(type);
        textView.setPaddingRelative(0,60,0, 0);
        setClickable(true);
        setFocusable(true);
        setOrientation(LinearLayout.VERTICAL);
        addView(textView);
        addView(imageView);
    }

    @Override
    public boolean performClick() {
        return true;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public Chess getChess() {
        return chess;
    }
}
