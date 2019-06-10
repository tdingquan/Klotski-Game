package com.unit.huarongdao;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

public class ChessList {
    private String TAG = "ChessList";
    private int[] typeArray = {0, 0, 0, 0, 0, 0, 0};
    private ArrayList<Chess> chessList;
    private ArrayList<Integer> colorList;

    ChessList(int difficulty) {
        chessList = new ArrayList<Chess>(10);
        initColorList();
        initChessList(difficulty);
    }

    private void initChessList(int difficulty) {
        switch (difficulty) {
            case 1:
                initChessList1();
                break;
            case 2:
                initChessList2();
                break;
            case 3:
                initChessList3();
                break;
            case 4:
                initChessList4();
                break;
            case 5:
                initChessList5();
                break;
            case 6:
                initChessList6();
                break;
            default:
        }

    }

    private void initChessList6() {
        chessList.add(new Chess(0, 0, 1, 1, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 1, typeArray));
        chessList.add(new Chess(0, 1, 1, 2, typeArray));
        chessList.add(new Chess(1, 2, 2, 1, typeArray));
        chessList.add(new Chess(3, 1, 1, 2, typeArray));
        chessList.add(new Chess(0, 3, 1, 1, typeArray));
        chessList.add(new Chess(1, 3, 2, 1, typeArray));
        chessList.add(new Chess(3, 3, 1, 1, typeArray));
        chessList.add(new Chess(1, 4, 2, 1, typeArray));
    }

    private void initChessList5() {
        chessList.add(new Chess(0, 0, 1, 2, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 2, typeArray));
        chessList.add(new Chess(0, 2, 1, 1, typeArray));
        chessList.add(new Chess(1, 2, 2, 1, typeArray));
        chessList.add(new Chess(3, 2, 1, 1, typeArray));
        chessList.add(new Chess(0, 3, 1, 1, typeArray));
        chessList.add(new Chess(1, 3, 2, 1, typeArray));
        chessList.add(new Chess(3, 3, 1, 1, typeArray));
        chessList.add(new Chess(1, 4, 2, 1, typeArray));
    }

    private void initChessList4() {
        chessList.add(new Chess(0, 0, 1, 1, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 1, typeArray));
        chessList.add(new Chess(0, 1, 1, 2, typeArray));
        chessList.add(new Chess(1, 2, 1, 2, typeArray));
        chessList.add(new Chess(3, 1, 1, 2, typeArray));
        chessList.add(new Chess(0, 3, 1, 1, typeArray));
        chessList.add(new Chess(3, 3, 1, 1, typeArray));
        chessList.add(new Chess(0, 4, 2, 1, typeArray));
        chessList.add(new Chess(2, 4, 2, 1, typeArray));
    }

    private void initChessList3() {
        chessList.add(new Chess(0, 0, 1, 2, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 2, typeArray));
        chessList.add(new Chess(0, 2, 1, 1, typeArray));
        chessList.add(new Chess(0, 3, 1, 1, typeArray));
        chessList.add(new Chess(1, 2, 1, 2, typeArray));
        chessList.add(new Chess(3, 2, 1, 1, typeArray));
        chessList.add(new Chess(3, 3, 1, 1, typeArray));
        chessList.add(new Chess(0, 4, 2, 1, typeArray));
        chessList.add(new Chess(2, 4, 2, 1, typeArray));
    }

    private void initChessList2() {
        chessList.add(new Chess(0, 0, 1, 2, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 2, typeArray));
        chessList.add(new Chess(0, 2, 1, 2, typeArray));
        chessList.add(new Chess(1, 2, 2, 1, typeArray));
        chessList.add(new Chess(1, 3, 2, 1, typeArray));
        chessList.add(new Chess(3, 2, 1, 1, typeArray));
        chessList.add(new Chess(3, 3, 1, 1, typeArray));
        chessList.add(new Chess(0, 4, 1, 1, typeArray));
        chessList.add(new Chess(3, 4, 1, 1, typeArray));
    }

    private void initChessList1() {
        chessList.add(new Chess(0, 0, 1, 2, typeArray));
        chessList.add(new Chess(1, 0, 2, 2, typeArray));
        chessList.add(new Chess(3, 0, 1, 2, typeArray));
        chessList.add(new Chess(0, 2, 1, 2, typeArray));
        chessList.add(new Chess(1, 2, 2, 1, typeArray));
        chessList.add(new Chess(1, 3, 1, 1, typeArray));
        chessList.add(new Chess(2, 3, 1, 1, typeArray));
        chessList.add(new Chess(3, 2, 1, 2, typeArray));
        chessList.add(new Chess(0, 4, 1, 1, typeArray));
        chessList.add(new Chess(3, 4, 1, 1, typeArray));
    }

    private void initColorList() {
        colorList = new ArrayList<Integer>(10);
        this.colorList.add(Color.rgb(50, 0, 0));
        this.colorList.add(Color.rgb(100, 0, 0));
        this.colorList.add(Color.rgb(150, 0, 0));
        this.colorList.add(Color.rgb(200, 0, 0));
        this.colorList.add(Color.rgb(0, 50, 0));
        this.colorList.add(Color.rgb(0, 100, 0));
        this.colorList.add(Color.rgb(0, 150, 0));
        this.colorList.add(Color.rgb(0, 0, 50));
        this.colorList.add(Color.rgb(0, 0, 100));
        this.colorList.add(Color.rgb(0, 0, 150));
        this.colorList.add(Color.rgb(0, 0, 200));
        Log.d("TAG", "" + this.colorList);
    }

    public ArrayList<Chess> getChessList() {
        return this.chessList;
    }
}
