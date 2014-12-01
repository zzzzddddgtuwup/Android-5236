package com.android.mobileapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NotiFreqActivity extends ActionBarActivity {
    private final String TAG = ((Object)this).getClass().getSimpleName();
    private NotificationManager mNotificationManager;
    private int interval = 10000;
    private Handler handler;
    private NotificationCompat.Builder mBuilder;
    private Notification mNotification;
    private int mID = 0;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "+++ In onCreate() +++");
        setContentView(R.layout.activity_noti_freq);
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        username = sharedPref.getString(getString(R.string.username),"zdg");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.arrow)
                        .setContentTitle("")
                        .setContentText("");



        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        //mNotificationManager.notify(0, mBuilder.build());
    }

    @Override

    public void onStart() {
        super.onStart();
        handler = new Handler();
        if (interval != 0) handler.postDelayed(runnable, interval);
        //Start a handler to update notification with certain interval

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
      /* do what you need to do */
            new getNotification().execute(username);
//            Set<Integer> notificationNumber = new HashSet<Integer>();
//            notificationNumber.add(1);
            //notificationNumber.add(2);
            //notificationNumber.add(3);
//            if (notificationNumber.contains(1)) {
//                mBuilder
//                        .setContentTitle("Question")
//                        .setContentText("Your question is Liked!");
//                mNotificationManager.notify(mID, mBuilder.build());
//                mID++;
//            }
//            if (notificationNumber.contains(2)) {
//                mBuilder
//                        .setContentTitle("Question")
//                        .setContentText("Your question is Answered!");
//                mNotificationManager.notify(mID, mBuilder.build());
//                mID++;
//            }
//            if (notificationNumber.contains(3)) {
//                mBuilder
//                        .setContentTitle("Answer")
//                        .setContentText("Your answer is Liked!");
//                mNotificationManager.notify(mID, mBuilder.build());
//                mID++;
//            }
//            if (interval != 0) handler.postDelayed(runnable, interval);
      /* and here comes the "trick" */
        }
    };

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        Log.e("tag", "OnRadioButtonClicked");
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_15_min:
                if (checked){
                    Log.e("tag", "15 min");
                    interval = 900000;
                    //interval = 1000;
                }
                    break;
            case R.id.radioButton_one_hour:
                if (checked){
                    Log.e("tag", "one hour");
                    interval = 3600000;
                    //interval = 3000;
                }
                    break;

            case R.id.radioButton_three_hour:
                if (checked) {
                    Log.e("tag", "three hour");
                    interval = 10800000;
                }
                    break;

            case R.id.radioButton_never:
                if (checked) {
                    interval = 0;
                }
                    break;

            case R.id.radioButton_testing:
                if (checked) {
                    interval = 10000;
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.noti_freq, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_login) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_noti_freq, container, false);
            return rootView;
        }
    }

    private class getNotification extends AsyncTask<String,Void,Collection<Integer>>{

        @Override
        protected Collection<Integer> doInBackground(String... usernames) {
            return UserSvc.getOrInit(getString(R.string.serverUrl))
                    .getNotificationSet(usernames[0]);
        }

        @Override
        protected void onPostExecute(Collection<Integer> notificationNumber){//Post notification accordingly
            if (notificationNumber.contains(1)) {
                Intent resultIntent = new Intent(NotiFreqActivity.this, MyQuestionActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                NotiFreqActivity.this,
                                mID,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder
                        .setContentTitle("Question")
                        .setContentText("Your question is Liked!")
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);
                mNotification = mBuilder.build();
                mNotification.defaults |= Notification.DEFAULT_VIBRATE;
                mNotification.defaults |= Notification.DEFAULT_SOUND;
                mNotificationManager.notify(mID, mNotification);
                mID++;
            }
            if (notificationNumber.contains(2)) {
                Intent resultIntent = new Intent(NotiFreqActivity.this, MyQuestionActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                NotiFreqActivity.this,
                                mID,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder
                        .setContentTitle("Question")
                        .setContentText("Your question is Answered!")
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);
                mNotification = mBuilder.build();
                mNotification.defaults |= Notification.DEFAULT_VIBRATE;
                mNotification.defaults |= Notification.DEFAULT_SOUND;
                mNotificationManager.notify(mID, mNotification);
                mID++;
            }
            if (notificationNumber.contains(3)) {
                Intent resultIntent = new Intent(NotiFreqActivity.this, MyAnswerActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                NotiFreqActivity.this,
                                mID,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder
                        .setContentTitle("Answer")
                        .setContentText("Your answer is Liked!")
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);
                mNotification = mBuilder.build();
                mNotification.defaults |= Notification.DEFAULT_VIBRATE;
                mNotification.defaults |= Notification.DEFAULT_SOUND;
                mNotificationManager.notify(mID, mNotification);
                mID++;
            }
            if (interval != 0) handler.postDelayed(runnable, interval);
        }
    }
}
