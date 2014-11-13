package com.android.mobileapp;
import com.google.common.base.Objects;

public class Forum {
	private long fid;
	
	private String name;

	public Forum(){}
	
	public Forum(String name){
		super();
		this.name = name;
	}
	
	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Forum) {
			Forum other = (Forum) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(name, other.name);
		} else {
			return false;
		}
	}
}
