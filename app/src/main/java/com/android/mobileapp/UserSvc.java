package com.android.mobileapp;

import retrofit.RestAdapter;

/**
 * Created by zzzzddddgtuwup on 11/13/14.
 */
public class UserSvc {
    private static UserSvcApi userSvc_;

    public static synchronized UserSvcApi getOrInit(String serverUrl) {
        if(userSvc_ == null){
            userSvc_ = new RestAdapter.Builder().setEndpoint(serverUrl)
                    .setLogLevel(RestAdapter.LogLevel.FULL).build().create(UserSvcApi.class);
        }
        return userSvc_;
    }

}
