package com.android.mobileapp;

/**
 * Created by zzzzddddgtuwup on 10/19/14.
 */
public class Question {
    private String contents;
    private int rate;

    public Question(String contents) {
        this.contents = contents;
        this.rate = 0;
    }

    public void like() {
        this.rate++;
    }

    public String getContents() {
        return this.contents;
    }

    public int getRate() {
        return this.rate;
    }
}
