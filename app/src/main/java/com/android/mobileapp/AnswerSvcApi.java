package com.android.mobileapp;

import java.util.Collection;

import retrofit.http.GET;
import retrofit.http.Query;

public interface AnswerSvcApi {
    public static final String USER_NAME = "username";

    public static final String QUESTION_ID = "question_id";

    public static final String ANSWER_ID = "answer_id";

    public static final String ANSWER_SVC_PATH = "/answer";

    public static final String ANSWER_CONTENT = "/content";

    public static final String ANSWER_USER_SEARCH_PATH =
            ANSWER_SVC_PATH + "/find";

    public static final String ANSWER_BY_QUESTION_ID_PATH =
            ANSWER_SVC_PATH + "/findbyId";

    public static final String ANSWER_RATE_PATH =
            ANSWER_SVC_PATH + "/rate";

    public static final String ANSWER_ADD_PATH =
            ANSWER_SVC_PATH + "/add";
    @GET(ANSWER_SVC_PATH)
    public Collection<Answer> getMyAnswerList();

    @GET(ANSWER_USER_SEARCH_PATH)
    public Collection<Answer> findByUserName(@Query(USER_NAME) String username);

    @GET(ANSWER_ADD_PATH)
    public boolean addAnswer(@Query(ANSWER_CONTENT) String content,
                             @Query(USER_NAME) String username, @Query(QUESTION_ID) long qid);

    @GET(ANSWER_BY_QUESTION_ID_PATH)
    public Collection<Answer> findByQuestionId(@Query(QUESTION_ID) long qid);

    @GET(ANSWER_RATE_PATH)
    public boolean rateAnswerById(@Query(ANSWER_ID) long aid);
}
