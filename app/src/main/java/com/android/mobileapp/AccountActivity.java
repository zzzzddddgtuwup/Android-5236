package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AccountActivity extends ActionBarActivity implements View.OnClickListener{
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        username = sharedPref.getString(getString(R.string.username), "zdg");
        TextView username_view = (TextView)findViewById(R.id.content_username);
        username_view.setText(username);

        RelativeLayout more_question_direct = (RelativeLayout)findViewById(R.id.question_more_direct);
        more_question_direct.setOnClickListener(this);

        RelativeLayout more_answer_direct = (RelativeLayout)findViewById(R.id.answer_more_direct);
        more_answer_direct.setOnClickListener(this);

        new getUserInfo().execute(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.question_more_direct:
                Intent myQuestion_intent = new Intent(AccountActivity.this, MyQuestionActivity.class);
                startActivity(myQuestion_intent);
                break;
            case R.id.answer_more_direct:
                Intent myAnswer_intent = new Intent(AccountActivity.this, MyAnswerActivity.class);
                startActivity(myAnswer_intent);
                break;
        }
    }

    private class getUserInfo extends AsyncTask<String, Void, User>{

        @Override
        protected User doInBackground(String... usernames) {
            return UserSvc.getOrInit(getString(R.string.serverUrl))
                    .getInfoByName(usernames[0]);
        }

        @Override
        protected void onPostExecute(User u){
            TextView userscore_view = (TextView)findViewById(R.id.content_point);
            userscore_view.setText(String.valueOf(u.getScore()));

            TextView user_question_count_view = (TextView)findViewById(R.id.user_total_question_num);
            user_question_count_view.setText(String.valueOf(u.getQuestion_count()));

            TextView user_answer_count_view = (TextView)findViewById(R.id.user_total_answer_num);
            user_answer_count_view.setText(String.valueOf(u.getAnswer_count()));
        }
    }
}
