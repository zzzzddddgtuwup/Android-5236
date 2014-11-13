package com.android.mobileapp;

import com.google.common.base.Objects;

public class Answer {
	private long aid;
	
	private String content;
	private int rate;
	private User user;
	private Question question;
	
	public Answer(){}
	
	public Answer(String content){
		super();
		this.content = content;
		this.rate = 0;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(content, rate);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Answer) {
			Answer other = (Answer) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(content, other.content)
					&& Objects.equal(rate, other.rate);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return "Answer content is" + content + ", rate is " + rate;
	}
}
