package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AccountActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.username), "zdg");
        int user_score = sharedPref.getInt(getString(R.string.user_score), -1);
        int user_question_count = sharedPref.getInt(getString(R.string.user_question_count),-1);
        int user_answer_count = sharedPref.getInt(getString(R.string.user_answer_count),-1);

        TextView username_view = (TextView)findViewById(R.id.content_username);
        username_view.setText(username);

        TextView userscore_view = (TextView)findViewById(R.id.content_point);
        userscore_view.setText(String.valueOf(user_score));

        TextView user_question_count_view = (TextView)findViewById(R.id.user_total_question_num);
        user_question_count_view.setText(String.valueOf(user_question_count));

        TextView user_answer_count_view = (TextView)findViewById(R.id.user_total_answer_num);
        user_answer_count_view.setText(String.valueOf(user_answer_count));

        RelativeLayout more_question_direct = (RelativeLayout)findViewById(R.id.question_more_direct);
        more_question_direct.setOnClickListener(this);

        RelativeLayout more_answer_direct = (RelativeLayout)findViewById(R.id.answer_more_direct);
        more_answer_direct.setOnClickListener(this);


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
}
