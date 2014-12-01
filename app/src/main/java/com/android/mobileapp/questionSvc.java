package com.android.mobileapp;

import retrofit.RestAdapter;

/**
 * Created by zzzzddddgtuwup on 11/13/14.
 */
//question service to connect to server
public class questionSvc {
    private static QuestionSvcApi questionSvc_;

    public static synchronized QuestionSvcApi getOrInit(String serverUrl){

        if(questionSvc_ == null){
            questionSvc_ = new RestAdapter.Builder()
                    .setEndpoint(serverUrl).setLogLevel(RestAdapter.LogLevel.FULL).build()
                    .create(QuestionSvcApi.class);
        }
        return questionSvc_;
    }

}
