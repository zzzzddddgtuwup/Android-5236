package com.android.mobileapp;

/**
 * Created by zzzzddddgtuwup on 10/19/14.
 */
public class Answer {
    private String contents;
    private int rate;

    public Answer(String contents) {
        this.contents = contents;
        this.rate = 0;
    }

    private void like() {
        this.rate++;
    }

    public int getRate() {
        return rate;
    }

    public String getContents() {
        return contents;
    }
}
