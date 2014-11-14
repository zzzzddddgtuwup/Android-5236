package com.android.mobileapp;

/**
 * Created by harrylew on 11/13/14.
 */
public class CurrentUserSession {
    private static CurrentUserSession sUserSession;
    private boolean isLoggedIn;

    private CurrentUserSession(){
        isLoggedIn = false;
    }

    public static CurrentUserSession getInstance(){
        if(sUserSession == null) {
            sUserSession = new CurrentUserSession();
        }

        return sUserSession;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}


