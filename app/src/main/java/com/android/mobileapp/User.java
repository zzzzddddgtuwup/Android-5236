package com.android.mobileapp;

import com.google.common.base.Objects;

public class User {
    private long uid;

    private String username;
    private String password;
    private int score;
    private int question_count;
    private int answer_count;
    private boolean notification_answerd;
    private boolean notification_answer_rated;
    private boolean notification_question_rated;

    public User(){}

    public User(String username, String password){
        super();
        this.username = username;
        this.password = password;
        this.score = 0;
        this.answer_count = 0;
        this.question_count = 0;
        clearNotification();
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(){
        this.score++;
    }


    public int getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(int question_count) {
        this.question_count = question_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public void addAnswer_count(){
        this.answer_count++;
    }

    public void addQuestion_count(){
        this.question_count++;
    }

    public void addNotification(int i){
        switch (i) {
            case 1:
                this.notification_question_rated = true;
                break;
            case 2:
                this.notification_answerd = true;
                break;
            case 3:
                this.notification_answer_rated = true;
                break;
        }
    }

    public void clearNotification(){
        this.notification_answer_rated = false;
        this.notification_answerd = false;
        this.notification_question_rated = false;
    }


    public boolean isNotification_answerd() {
        return notification_answerd;
    }

    public void setNotification_answerd(boolean notification_answerd) {
        this.notification_answerd = notification_answerd;
    }

    public boolean isNotification_answer_rated() {
        return notification_answer_rated;
    }

    public void setNotification_answer_rated(boolean notification_answer_rated) {
        this.notification_answer_rated = notification_answer_rated;
    }

    public boolean isNotification_question_rated() {
        return notification_question_rated;
    }

    public void setNotification_question_rated(boolean notification_question_rated) {
        this.notification_question_rated = notification_question_rated;
    }

    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(username, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(username, other.username)
                    && Objects.equal(password, other.password);
        } else {
            return false;
        }
    }

    public String toString(){
        return username + " " + password + " " + score;
    }
}
