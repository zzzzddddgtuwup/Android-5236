package com.android.mobileapp;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface QuestionSvcApi {
	public static final String USER_NAME = "username";
	
	public static final String FORUM_ID = "fid";
	
	public static final String QUESTION_SVC_PATH = "/question";
	
	public static final String QUESTION_USER_SEARCH_PATH = 
			QUESTION_SVC_PATH + "/findu";
	
	public static final String QUESTION_FORUM_SEARCH_PATH = 
			QUESTION_SVC_PATH + "/findf";
	
	@GET(QUESTION_SVC_PATH)
	public Collection<Question> getQuestionList();
	
	@GET(QUESTION_USER_SEARCH_PATH)
	public Collection<Question> findByUserName(@Query(USER_NAME) String username);
	
	@GET(QUESTION_FORUM_SEARCH_PATH)
	public Collection<Question> findByForumId(@Query(FORUM_ID) long fid);
	
	@POST(QUESTION_SVC_PATH)
	public boolean addQuestion(@Body Question q);
}
