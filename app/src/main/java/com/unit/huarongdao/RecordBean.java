package com.unit.huarongdao;

public class RecordBean {
    private int difficulty;
    private int time;
    private int step;
    private String record_name;

    public RecordBean(int difficulty, int time, int step, String record_name) {
        super();
        this.difficulty = difficulty;
        this.time = time;
        this.step = step;
        this.record_name = record_name;
    }

    @Override
    public String toString() {
        return "关卡" + difficulty + "\t" + record_name + "\n\t耗时: " + time + " 步骤: " + step + "\n";
    }
}
