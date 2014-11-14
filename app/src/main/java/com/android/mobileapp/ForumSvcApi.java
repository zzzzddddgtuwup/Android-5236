package com.android.mobileapp;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ForumSvcApi {
    public static final String FORUM_PART_NAME = "partname";
    // The path where we expect the VideoSvc to live
    public static final String FORUM_SVC_PATH = "/forum";

    public static final String FORUM_SEARCH_PATH = FORUM_SVC_PATH + "/find";

    public static final String FORUM_NAME_SEARCH_PATH = FORUM_SVC_PATH + "/namesearch";

    @GET(FORUM_SVC_PATH)
    public Collection<Forum> getForumList();

    @POST(FORUM_SVC_PATH)
    public boolean addForum(@Body Forum f);

    @GET(FORUM_NAME_SEARCH_PATH)
    public Collection<Forum> searchByName(@Query(FORUM_PART_NAME) String partname);
}
