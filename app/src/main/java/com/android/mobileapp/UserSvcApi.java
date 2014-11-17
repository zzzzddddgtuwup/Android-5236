package com.android.mobileapp;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface UserSvcApi {
    public static final String USER_NAME = "username";

    public static final String USER_SVC_PATH = "/user";

    public static final String USER_VALIDATE_PATH =
            USER_SVC_PATH + "/validate";

    public static final String USER_NOTIFICATION_PATH =
            USER_SVC_PATH + "/notification";


    @GET(USER_SVC_PATH)
    public Collection<User> getUserList();

    @POST(USER_SVC_PATH)
    public boolean addUser(@Body User user);

    @POST(USER_VALIDATE_PATH)
    public User validate(@Body User user);

    @GET(USER_NOTIFICATION_PATH)
    public Collection<Integer> getNotificationSet(@Query(USER_NAME) String username);
}
