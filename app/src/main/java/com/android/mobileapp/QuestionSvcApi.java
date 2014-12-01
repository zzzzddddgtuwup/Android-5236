package com.android.mobileapp;


import java.util.Collection;

import retrofit.http.GET;
import retrofit.http.Query;
//API and path provided by server
public interface QuestionSvcApi {
    public static final String USER_NAME = "username";

    public static final String QUESTION_CONTENT = "question_content";

    public static final String FORUM_ID = "fid";

    public static final String SEARCH_KEY ="key";

    public static final String QUESTION_ID = "question_id";

    public static final String QUESTION_SVC_PATH = "/question";

    public static final String QUESTION_USER_SEARCH_PATH =
            QUESTION_SVC_PATH + "/findu";

    public static final String QUESTION_FORUM_SEARCH_PATH =
            QUESTION_SVC_PATH + "/search";

    public static final String QUESTION_FORUM_GET_PATH =
            QUESTION_SVC_PATH + "/findf";

    public static final String QUESTION_RATE_PATH =
            QUESTION_SVC_PATH + "/rate";

    public static final String QUESTION_SORT_PATH =
            QUESTION_SVC_PATH + "/sort";

    public static final String QUESTION_ADD_PATH =
            QUESTION_SVC_PATH + "/add";

    @GET(QUESTION_SVC_PATH)
    public Collection<Question> getQuestionList();

    @GET(QUESTION_USER_SEARCH_PATH)
    public Collection<Question> findByUserName(@Query(USER_NAME) String username);

    @GET(QUESTION_FORUM_GET_PATH)
    public Collection<Question> findByForumId(@Query(FORUM_ID) long fid);

    @GET(QUESTION_ADD_PATH)
    public boolean addQuestion(@Query(QUESTION_CONTENT) String content, @Query(USER_NAME)String username,@Query(FORUM_ID)long fid);

    @GET(QUESTION_FORUM_SEARCH_PATH)
    public Collection<Question> searchByQuestionInForum(
            @Query(SEARCH_KEY)String key,@Query(FORUM_ID) long fid);

    @GET(QUESTION_RATE_PATH)
    public boolean rateQuestionById(@Query(QUESTION_ID) long qid);

    @GET(QUESTION_SORT_PATH)
    public Collection<Question> getSortedQuestionList(@Query(FORUM_ID) long fid);
}
