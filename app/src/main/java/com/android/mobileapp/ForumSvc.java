package com.android.mobileapp;

import retrofit.RestAdapter;

/**
 * Created by zzzzddddgtuwup on 11/13/14.
 */
//forum service to connect to server
public class ForumSvc {
    private static ForumSvcApi forumSvc_;

    public static synchronized ForumSvcApi getOrInit(String serverUrl){
        if(forumSvc_ == null){
            forumSvc_ = new RestAdapter.Builder().setEndpoint(serverUrl)
                    .setLogLevel(RestAdapter.LogLevel.FULL).build()
                    .create(ForumSvcApi.class);
        }
        return forumSvc_;
    }
}
