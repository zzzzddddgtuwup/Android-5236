package com.android.mobileapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzzddddgtuwup on 10/19/14.
 */
public class User {
    private String username;
    private String password;
    private int score;

    private List<Question> myQuestions;
    private List<Answer> myAnswers;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
        this.myQuestions = new ArrayList<Question>();
        this.myAnswers = new ArrayList<Answer>();
    }

    public void addQuestion(Question q) {
        myQuestions.add(q);
    }

    public void addAnswer(Answer ans) {
        myAnswers.add(ans);
    }

    public List<Question> getMyQuestions(){
        return myQuestions;
    }

    public List<Answer> getMyAnswers() {
        return myAnswers;
    }
}
