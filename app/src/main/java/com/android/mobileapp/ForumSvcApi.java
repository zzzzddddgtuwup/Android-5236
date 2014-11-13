package com.android.mobileapp;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ForumSvcApi {

	// The path where we expect the VideoSvc to live
	public static final String FORUM_SVC_PATH = "/forum";
	
	public static final String FORUM_SEARCH_PATH = FORUM_SVC_PATH + "/find";
	
	@GET(FORUM_SVC_PATH)
	public Collection<Forum> getForumList();
	
	@POST(FORUM_SVC_PATH)
	public boolean addForum(@Body Forum f);
}
