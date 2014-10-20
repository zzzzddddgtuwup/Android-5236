package com.android.mobileapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzzddddgtuwup on 10/19/14.
 */
public class QandA {
    public Question question;
    public List<Answer> answers;
    int rate;

    public QandA(Question question) {
        this.question = question;
        this.answers = new ArrayList<Answer>();
    }

    public void addAnswer(Answer answer) {
         answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question getQuestion() {
        return question;
    }


}
