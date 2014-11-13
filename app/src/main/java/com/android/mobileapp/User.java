package com.android.mobileapp;

import com.google.common.base.Objects;

public class User {
	private long uid;
	
	private String username;
	private String password;
	
	public User(){}
	
	public User(String username, String password){
		super();
		this.username = username;
		this.password = password;
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
		return username + " " + password;
	}
}
