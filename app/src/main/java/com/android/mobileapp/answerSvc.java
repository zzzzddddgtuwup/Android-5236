package com.android.mobileapp;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * Created by zzzzddddgtuwup on 11/13/14.
 */
class AnswerSvc {
    private static AnswerSvcApi answerSvc_;

    public static synchronized AnswerSvcApi getOrInit(String serverUrl){
        if(answerSvc_ == null) {
            answerSvc_ = new RestAdapter.Builder().setEndpoint(serverUrl)
                    .setLogLevel(LogLevel.FULL).build().create(AnswerSvcApi.class);
        }
        return answerSvc_;
    }
}
