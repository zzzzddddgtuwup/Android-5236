package com.android.mobileapp;

import com.google.common.base.Objects;

public class User {
    private long uid;

    private String username;
    private String password;
    private int score;
    private int question_count;
    private int answer_count;

    public User(){}

    public User(String username, String password){
        super();
        this.username = username;
        this.password = password;
        this.score = 0;
        this.answer_count = 0;
        this.question_count = 0;
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
